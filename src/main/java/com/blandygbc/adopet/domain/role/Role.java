package com.blandygbc.adopet.domain.role;

import java.io.Serializable;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "roles")
@Entity(name = "Role")
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode(of = "id")
public class Role implements Serializable {
    private static final long serialVersionUID = 2405172041950251807L;

    private static final String ROLE_PREFIX = "ROLE_";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;

    public void updateInfo(RoleUpdateModel updateRole) {
        if (updateRole.name() != null) {
            this.name = updateRole.name();
        }
        if (updateRole.description() != null) {
            this.description = updateRole.description();
        }
    }

    public String getSpringSecurityRole() {
        return ROLE_PREFIX + this.name;
    }

}
