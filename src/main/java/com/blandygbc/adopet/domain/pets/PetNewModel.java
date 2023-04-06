package com.blandygbc.adopet.domain.pets;

import org.hibernate.validator.constraints.URL;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record PetNewModel(
                @NotBlank String name,
                @NotBlank String personality,
                @NotBlank String age,
                @NotBlank @URL String image,
                @NotNull PetSpecies species,
                @NotNull PetSize size,
                @NotNull Long shelterId) {

}
