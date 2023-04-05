package com.blandygbc.adopet.domain.model.role;

import jakarta.validation.constraints.NotBlank;

public record RoleNewModel(
                @NotBlank String name,
                @NotBlank String description) {

}
