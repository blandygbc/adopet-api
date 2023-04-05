package com.blandygbc.adopet.domain.role;

import com.blandygbc.adopet.domain.model.role.RoleModel;
import com.blandygbc.adopet.domain.model.role.RoleNewModel;
import com.blandygbc.adopet.domain.model.role.RoleUpdateModel;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "roles")
@Entity(name = "Role")
@Getter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;

    public Role(RoleNewModel roleNewModel) {
        this.name = roleNewModel.name();
        this.description = roleNewModel.description();
    }

    public Role(RoleModel roleModel) {
        this.id = roleModel.id();
        this.name = roleModel.name();
        this.description = roleModel.description();
    }

    public void updateInfo(RoleUpdateModel updateRole) {
        if (updateRole.name() != null) {
            this.name = updateRole.name();
        }
        if (updateRole.description() != null) {
            this.description = updateRole.description();
        }
    }

}
