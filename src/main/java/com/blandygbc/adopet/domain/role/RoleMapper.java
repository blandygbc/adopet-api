package com.blandygbc.adopet.domain.role;

import org.springframework.stereotype.Component;

@Component
public class RoleMapper {

    public Role modelToEntity(RoleModel roleModel) {
        return Role.builder()
                .id(roleModel.id())
                .name(roleModel.name())
                .description(roleModel.description())
                .build();
    }

    public Role newModelToEntity(RoleNewModel newRole) {
        return Role.builder()
                .name(newRole.name())
                .description(newRole.description())
                .build();

    }

    public RoleModel entityToModel(Role role) {
        return RoleModel.builder()
                .id(role.getId())
                .name(role.getName())
                .description(role.getDescription())
                .build();
    }
}
