package com.blandygbc.adopet.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blandygbc.adopet.domain.exception.EmptyListException;
import com.blandygbc.adopet.domain.role.BasicRoles;
import com.blandygbc.adopet.domain.shelter.ShelterMapper;
import com.blandygbc.adopet.domain.shelter.ShelterModel;
import com.blandygbc.adopet.domain.shelter.ShelterNewModel;
import com.blandygbc.adopet.domain.shelter.ShelterRepository;
import com.blandygbc.adopet.domain.shelter.ShelterUpdateModel;
import com.blandygbc.adopet.domain.user.AuthService;
import com.blandygbc.adopet.domain.user.User;
import com.blandygbc.adopet.util.JsonMessage;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@RestController
@RequestMapping("shelters")
public class ShelterController {

    @Autowired
    private ShelterRepository repository;

    @Autowired
    private AuthService authService;

    @Autowired
    private ShelterMapper mapper;

    @PostMapping
    @Transactional
    // @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<ShelterModel> add(@Valid @RequestBody ShelterNewModel newShelter) {
        User user = authService.createUser(newShelter.email(), newShelter.password(), BasicRoles.SHELTER.getId());
        var savedShelter = repository.save(mapper.newModelToEntity(newShelter, user));
        return ResponseEntity.ok(mapper.entityToModel(savedShelter));
    }

    @GetMapping
    @SecurityRequirement(name = "bearer-key")
    public ResponseEntity<List<ShelterModel>> getAll() {
        List<ShelterModel> shelters = repository.findAll().stream()
                .map(s -> mapper.entityToModel(s))
                .collect(Collectors.toList());
        if (shelters.isEmpty()) {
            throw new EmptyListException();
        }
        return ResponseEntity.ok(shelters);
    }

    @PutMapping
    @Transactional
    @SecurityRequirement(name = "bearer-key")
    public ResponseEntity<ShelterModel> update(@Valid @RequestBody ShelterUpdateModel updateShelter) {
        var shelter = repository.getReferenceById(updateShelter.id());
        shelter.updateInfo(updateShelter);
        return ResponseEntity.ok(mapper.entityToModel(shelter));
    }

    @DeleteMapping("/{shelterId}")
    @Transactional
    @SecurityRequirement(name = "bearer-key")
    public ResponseEntity<JsonMessage> delete(@PathVariable Long shelterId) {
        Integer result = repository.deleteShelterById(shelterId);
        if (result == 0) {
            throw new EntityNotFoundException();
        }
        return ResponseEntity.ok(new JsonMessage("Removido com sucesso!"));
    }

    @GetMapping(value = "/{shelterId}")
    @SecurityRequirement(name = "bearer-key")
    public ResponseEntity<ShelterModel> detail(@PathVariable Long shelterId) {
        var shelter = repository.getReferenceById(shelterId);
        return ResponseEntity.ok(mapper.entityToModel(shelter));
    }

}
