package com.blandygbc.adopet.domain.tutor;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Builder;

@Builder
public record TutorNewModel(
        @NotBlank @Size(min = 2, max = 100) String name,
        @NotBlank @Size(max = 255, message = "O e-mail deve conter no máximo {max} caracteres") @Email String email,
        @NotBlank @Size(min = 8, max = 30, message = "A senha deve ter entre {min} a {max} caracteres") String password) {

}
