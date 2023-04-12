package com.blandygbc.adopet.domain.role;

import jakarta.validation.constraints.NotBlank;

public record RoleNewModel(
        @NotBlank String name,
        @NotBlank String description) {

}
