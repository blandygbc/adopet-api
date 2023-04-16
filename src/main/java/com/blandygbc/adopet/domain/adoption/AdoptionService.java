package com.blandygbc.adopet.domain.adoption;

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

    @Transactional
    public ResponseEntity<AdoptionModel> adopt(Pet pet, Tutor tutor) {
        petService.adoptPet(pet);
        Adoption savedAdoption = repository.save(Adoption.buildNew(pet, tutor));
        return ResponseEntity.ok(AdoptionModel.modelFromEntity(savedAdoption));
    }

    public ResponseEntity<JsonMessage> deleteAdoption(Long adoptionId) {
        Adoption adoption = repository.getReferenceById(adoptionId);
        Pet pet = adoption.getPet();
        repository.delete(adoption);
        petService.setPetAvailable(pet);
        return ResponseEntity.ok(new JsonMessage("Removido com sucesso!"));
    }
}
