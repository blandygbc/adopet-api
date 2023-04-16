package com.blandygbc.adopet.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blandygbc.adopet.domain.adoption.AdoptionModel;
import com.blandygbc.adopet.domain.adoption.AdoptionNewModel;
import com.blandygbc.adopet.domain.adoption.AdoptionRepository;
import com.blandygbc.adopet.domain.adoption.AdoptionService;
import com.blandygbc.adopet.domain.exception.EmptyListException;
import com.blandygbc.adopet.domain.pets.PetRepository;
import com.blandygbc.adopet.domain.tutor.TutorRepository;
import com.blandygbc.adopet.util.JsonMessage;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/adoptions")
public class AdoptionController {
    @Autowired
    private AdoptionRepository repository;
    @Autowired
    private PetRepository petRepository;
    @Autowired
    private TutorRepository tutorRepository;
    @Autowired
    private AdoptionService service;

    @PostMapping
    @Transactional
    public ResponseEntity<AdoptionModel> add(@Valid @RequestBody AdoptionNewModel newAdoption) {
        var pet = petRepository.getReferenceById(newAdoption.pet());
        var tutor = tutorRepository.getReferenceById(newAdoption.tutor());
        return service.adopt(pet, tutor);

    }

    @GetMapping
    public ResponseEntity<Page<AdoptionModel>> getAll(@PageableDefault(size = 10) Pageable page) {
        Page<AdoptionModel> adoptionPage = repository.findAll(page)
                .map(AdoptionModel::modelFromEntity);
        if (adoptionPage.isEmpty()) {
            throw new EmptyListException();
        }
        return ResponseEntity.ok(adoptionPage);
    }

    @DeleteMapping("/{adoptionId}")
    @Transactional
    public ResponseEntity<JsonMessage> delete(@RequestHeader String role,
            @PathVariable Long adoptionId) {
        return service.deleteAdoption(role, adoptionId);
    }
}
