package com.blandygbc.adopet.domain.pets;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;

@Service
public class PetService {
    @Autowired
    private PetRepository repository;

    @Transactional
    public void adoptPet(Pet pet) {
        pet.adopt();
        repository.save(pet);
    }
}
