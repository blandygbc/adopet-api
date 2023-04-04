package com.blandygbc.adopet.domain.tutor;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface TutorRepository extends JpaRepository<Tutor, Long> {
    @Modifying
    @Query(value = "DELETE FROM Tutor t where t.id = ?1")
    Integer deleteTutorById(Long id);
}
