package com.blandygbc.adopet.domain.adoption;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface AdoptionRepository extends JpaRepository<Adoption, Long> {
    @Modifying
    @Query(value = "DELETE FROM Adoption a where a.id = ?1")
    Integer deleteAdoptionById(Long adoptionId);
}
