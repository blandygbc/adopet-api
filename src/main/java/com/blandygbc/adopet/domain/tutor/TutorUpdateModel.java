package com.blandygbc.adopet.domain.tutor;

import org.hibernate.validator.constraints.URL;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

public record TutorUpdateModel(
        @NotNull Long id,
        String name,
        @Email String email,
        String password,
        String phone,
        String city,
        String state,
        String about,
        @URL String image) {

}
