package com.blandygbc.adopet.domain.tutor;

import org.hibernate.validator.constraints.URL;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;

@Builder
public record TutorUpdateModel(
                @NotNull Long id,
                @Size(min = 2, max = 100) String name,
                @Size(max = 255, message = "O e-mail deve conter no máximo {max} caracteres") @Email String email,
                @Size(min = 8, max = 30, message = "A senha deve ter entre {min} a {max} caracteres") String password,
                @Size(min = 10, max = 11) String phone,
                String city,
                String state,
                String about,
                @URL String image) {

}
