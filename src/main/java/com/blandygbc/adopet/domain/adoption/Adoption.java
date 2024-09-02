package com.blandygbc.adopet.domain.adoption;

import java.time.LocalDateTime;

import com.blandygbc.adopet.domain.pets.Pet;
import com.blandygbc.adopet.domain.tutor.Tutor;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "adoptions")
@Entity(name = "Adoption")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(of = "id")
public class Adoption {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne
    Pet pet;
    @ManyToOne
    Tutor tutor;
    LocalDateTime date;
}
