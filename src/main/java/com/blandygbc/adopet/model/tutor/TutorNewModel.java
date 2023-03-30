package com.blandygbc.adopet.model.tutor;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record TutorNewModel(
        @NotBlank String name,
        @NotBlank @Email String email,
        @NotBlank String password) {

}
