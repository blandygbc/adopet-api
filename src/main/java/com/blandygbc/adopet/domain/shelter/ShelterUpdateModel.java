package com.blandygbc.adopet.domain.shelter;

import org.hibernate.validator.constraints.URL;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

public record ShelterUpdateModel(
                @NotNull Long id,
                String name,
                @URL String image,
                String about,
                String city,
                String state,
                String phone,
                @Email String email,
                String password) {

}
