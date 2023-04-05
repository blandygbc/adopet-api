package com.blandygbc.adopet.domain.model.tutor;

import com.blandygbc.adopet.domain.model.role.RoleModel;

import jakarta.validation.constraints.NotNull;

public record TutorUpdateModel(
                @NotNull Long id,
                String name,
                String email,
                String password,
                Integer phone,
                String city,
                String about,
                String image,
                RoleModel role) {

}
