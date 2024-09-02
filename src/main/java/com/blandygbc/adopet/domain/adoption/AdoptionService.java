package com.blandygbc.adopet.domain.adoption;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.blandygbc.adopet.domain.pets.Pet;
import com.blandygbc.adopet.domain.pets.PetService;
import com.blandygbc.adopet.domain.tutor.Tutor;
import com.blandygbc.adopet.util.JsonMessage;

import jakarta.transaction.Transactional;

@Service
public class AdoptionService {
    @Autowired
    private AdoptionRepository repository;
    @Autowired
    private PetService petService;

    @Autowired
    private AdoptionMapper mapper;

    @Transactional
    public ResponseEntity<AdoptionModel> adopt(Pet pet, Tutor tutor) {
        petService.adoptPet(pet);
        Adoption newAdoption = Adoption.builder()
                .pet(pet)
                .tutor(tutor)
                .date(LocalDateTime.now())
                .build();
        Adoption savedAdoption = repository.save(newAdoption);
        return ResponseEntity.ok(mapper.entityToModel(savedAdoption));
    }

    public ResponseEntity<JsonMessage> deleteAdoption(Long adoptionId) {
        Adoption adoption = repository.getReferenceById(adoptionId);
        Pet pet = adoption.getPet();
        repository.delete(adoption);
        petService.setPetAvailable(pet);
        return ResponseEntity.ok(new JsonMessage("Removido com sucesso!"));
    }
}
