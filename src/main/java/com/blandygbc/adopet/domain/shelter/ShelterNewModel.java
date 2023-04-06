package com.blandygbc.adopet.domain.shelter;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record ShelterNewModel(
        @NotBlank String name,
        String image,
        String about,
        @NotBlank String city,
        @NotBlank String phone,
        @NotBlank @Email String email,
        @NotBlank String password) {

}
