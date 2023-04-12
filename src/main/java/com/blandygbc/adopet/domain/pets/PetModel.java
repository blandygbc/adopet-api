package com.blandygbc.adopet.domain.pets;

import com.blandygbc.adopet.domain.shelter.ShelterModel;

public record PetModel(
        Long id,
        String name,
        String personality,
        String age,
        String image,
        PetSpecies species,
        PetSize size,
        PetStatus status,
        ShelterModel shelter) {

    public static PetModel modelFromEntity(Pet pet) {
        return new PetModel(
                pet.getId(),
                pet.getName(),
                pet.getPersonality(),
                pet.getAge(),
                pet.getImage(),
                pet.getSpecies(),
                pet.getSize(),
                pet.getStatus(),
                ShelterModel.modelFromEntity(pet.getShelter()));
    }

}
