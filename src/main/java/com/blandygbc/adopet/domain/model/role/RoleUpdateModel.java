package com.blandygbc.adopet.domain.model.role;

import jakarta.validation.constraints.NotNull;

public record RoleUpdateModel(
                @NotNull Long id,
                String name,
                String description) {

}
