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
import com.blandygbc.adopet.domain.role.Role;
import com.blandygbc.adopet.domain.role.RoleMapper;
import com.blandygbc.adopet.domain.role.RoleModel;
import com.blandygbc.adopet.domain.role.RoleNewModel;
import com.blandygbc.adopet.domain.role.RoleRepository;
import com.blandygbc.adopet.domain.role.RoleUpdateModel;
import com.blandygbc.adopet.util.JsonMessage;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/roles")
@SecurityRequirement(name = "bearer-key")
public class RoleController {

    @Autowired
    private RoleRepository repository;

    @Autowired
    private RoleMapper mapper;

    @PostMapping
    public ResponseEntity<RoleModel> add(@Valid @RequestBody RoleNewModel newRole) {
        Role savedRole = repository.save(mapper.newModelToEntity(newRole));
        return ResponseEntity.ok(mapper.entityToModel(savedRole));
    }

    @GetMapping
    public ResponseEntity<List<RoleModel>> getAll() {
        List<RoleModel> roles = repository.findAll().stream()
                .map(r -> mapper.entityToModel(r))
                .collect(Collectors.toList());
        if (roles.isEmpty()) {
            throw new EmptyListException();
        }
        return ResponseEntity.ok(roles);
    }

    @PutMapping
    @Transactional
    public ResponseEntity<RoleModel> update(@Valid @RequestBody RoleUpdateModel updateRole) {
        var role = repository.getReferenceById(updateRole.id());
        role.updateInfo(updateRole);
        return ResponseEntity.ok(mapper.entityToModel(role));
    }

    @DeleteMapping("/{roleId}")
    @Transactional
    public ResponseEntity<JsonMessage> delete(@PathVariable Long roleId) {
        Integer result = repository.deleteRoleById(roleId);
        if (result == 0) {
            throw new EntityNotFoundException();
        }
        return ResponseEntity.ok(new JsonMessage("Removido com sucesso!"));
    }

}
