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
import com.blandygbc.adopet.domain.pets.Pet;
import com.blandygbc.adopet.domain.pets.PetModel;
import com.blandygbc.adopet.domain.pets.PetNewModel;
import com.blandygbc.adopet.domain.pets.PetRepository;
import com.blandygbc.adopet.domain.pets.PetUpdateModel;
import com.blandygbc.adopet.domain.shelter.ShelterRepository;
import com.blandygbc.adopet.util.JsonMessage;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@RestController
@RequestMapping("pets")
public class PetController {

    @Autowired
    private PetRepository repository;

    @Autowired
    private ShelterRepository shelterRepository;

    @PostMapping
    @Transactional
    // @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<PetModel> add(@Valid @RequestBody PetNewModel newPet) {
        var shelter = shelterRepository.getReferenceById(newPet.shelterId());
        var savedPet = repository.save(new Pet(newPet, shelter));
        return ResponseEntity.ok(new PetModel(savedPet));
    }

    @GetMapping
    public ResponseEntity<List<PetModel>> getAll() {
        List<PetModel> shelters = repository.findAll().stream()
                .map(PetModel::new)
                .collect(Collectors.toList());
        if (shelters.isEmpty()) {
            throw new EmptyListException();
        }
        return ResponseEntity.ok(shelters);
    }

    @PutMapping
    @Transactional
    public ResponseEntity<PetModel> update(@Valid @RequestBody PetUpdateModel updatePet) {
        var pet = repository.getReferenceById(updatePet.id());
        pet.updateInfo(updatePet);
        return ResponseEntity.ok(new PetModel(pet));
    }

    @DeleteMapping("/{petId}")
    @Transactional
    public ResponseEntity<JsonMessage> delete(@PathVariable Long petId) {
        Integer result = repository.deletePetById(petId);
        if (result == 0) {
            throw new EntityNotFoundException();
        }
        return ResponseEntity.ok(new JsonMessage("Removido com sucesso!"));
    }

    @GetMapping(value = "/{petId}")
    public ResponseEntity<PetModel> detail(@PathVariable Long petId) {
        var pet = repository.getReferenceById(petId);
        return ResponseEntity.ok(new PetModel(pet));
    }

}
