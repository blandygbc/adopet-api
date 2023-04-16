package com.blandygbc.adopet.domain.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record UserLoginModel(
                @NotBlank @Email String email,
                @NotBlank String password) {

}
