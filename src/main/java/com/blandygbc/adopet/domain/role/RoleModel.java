package com.blandygbc.adopet.domain.role;

import lombok.Builder;

@Builder
public record RoleModel(Long id, String name, String description) {

    public static RoleModel modelFromEntity(Role role) {
        return new RoleModel(
                role.getId(),
                role.getName(),
                role.getDescription());
    }
}
