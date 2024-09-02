package com.blandygbc.adopet.domain.pets;

import com.blandygbc.adopet.domain.shelter.ShelterModel;

import lombok.Builder;

@Builder
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
}
