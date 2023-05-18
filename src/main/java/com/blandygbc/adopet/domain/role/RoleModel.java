package com.blandygbc.adopet.domain.role;

import lombok.Builder;

@Builder
public record RoleModel(Long id, String name, String description) {
}
