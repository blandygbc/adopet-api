package com.blandygbc.adopet.domain.user;

import com.blandygbc.adopet.domain.role.RoleModel;

import jakarta.validation.constraints.Email;

public record UserModel(
        Long id,
        @Email String email,
        RoleModel role) {

    public static UserModel modelFromEntity(User user) {
        return new UserModel(
                user.getId(),
                user.getEmail(),
                RoleModel.modelFromEntity(user.getRole()));
    }

}
