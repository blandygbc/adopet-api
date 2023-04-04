package com.blandygbc.adopet.domain.model.tutor;

import jakarta.validation.constraints.NotNull;

public record TutorUpdateModel(
        @NotNull Long id,
        String name,
        String email,
        String password) {

}
