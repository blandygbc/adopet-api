package com.blandygbc.adopet.domain.tutor;

import jakarta.validation.constraints.NotNull;

public record TutorUpdateModel(
        @NotNull Long id,
        String name,
        String email,
        String password,
        String phone,
        String city,
        String about,
        String image) {

}
