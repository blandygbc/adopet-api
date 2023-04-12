package com.blandygbc.adopet.domain.pets;

import org.hibernate.validator.constraints.URL;

import jakarta.validation.constraints.NotNull;

public record PetUpdateModel(
        @NotNull Long id,
        String name,
        String personality,
        String age,
        @URL String image,
        PetSize size,
        PetStatus status) {
}
