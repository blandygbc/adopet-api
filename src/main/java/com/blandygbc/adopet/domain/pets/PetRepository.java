package com.blandygbc.adopet.domain.pets;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface PetRepository extends JpaRepository<Pet, Long> {
    @Modifying
    @Query(value = "DELETE FROM Pet p where p.id = ?1")
    Integer deletePetById(Long id);
}
