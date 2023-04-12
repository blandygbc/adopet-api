package com.blandygbc.adopet.domain.role;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface RoleRepository extends JpaRepository<Role, Long> {

    @Modifying
    @Query(value = "DELETE FROM Role r where r.id = ?1")
    Integer deleteRoleById(Long roleId);

}
