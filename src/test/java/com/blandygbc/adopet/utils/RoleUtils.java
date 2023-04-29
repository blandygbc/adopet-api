package com.blandygbc.adopet.utils;

import com.blandygbc.adopet.domain.role.BasicRoles;
import com.blandygbc.adopet.domain.role.Role;
import com.blandygbc.adopet.domain.role.RoleModel;

public abstract class RoleUtils {
    public static Role getTutorRole() {
        return Role.builder()
                .id(BasicRoles.TUTOR.getId())
                .name(BasicRoles.TUTOR.name())
                .description("Tutores dos pets")
                .build();
    }

    public static RoleModel getTutorRoleModel() {
        return RoleModel.builder()
                .id(BasicRoles.TUTOR.getId())
                .name(BasicRoles.TUTOR.name())
                .description("Tutores dos pets")
                .build();
    }
}
