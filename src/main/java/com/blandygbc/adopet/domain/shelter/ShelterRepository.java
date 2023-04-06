package com.blandygbc.adopet.domain.shelter;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface ShelterRepository extends JpaRepository<Shelter, Long> {
    @Modifying
    @Query(value = "DELETE FROM Shelter s where s.id = ?1")
    Integer deleteShelterById(Long id);
}
