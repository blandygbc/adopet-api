package com.blandygbc.adopet.domain.user;

import com.blandygbc.adopet.domain.role.RoleModel;

import jakarta.validation.constraints.Email;
import lombok.Builder;

@Builder
public record UserModel(
        Long id,
        @Email String email,
        RoleModel role) {

}
