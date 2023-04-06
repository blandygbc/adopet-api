package com.blandygbc.adopet.domain.role;

public record RoleModel(Long id, String name, String description) {
    public RoleModel(Role role) {
        this(role.getId(), role.getName(), role.getDescription());
    }
}
