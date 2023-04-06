package com.blandygbc.adopet.domain.shelter;

import org.hibernate.validator.constraints.URL;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record ShelterNewModel(
        @NotBlank String name,
        @NotBlank @URL String image,
        @NotBlank String about,
        @NotBlank String city,
        @NotBlank String state,
        @NotBlank String phone,
        @NotBlank @Email String email,
        @NotBlank String password) {

}
