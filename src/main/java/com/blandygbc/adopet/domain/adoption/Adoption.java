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
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "adoptions")
@Entity(name = "Adoption")
@Getter
@NoArgsConstructor
@AllArgsConstructor
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

    public static Adoption entityFromModel(AdoptionModel adoptionModel) {
        return new Adoption(
                adoptionModel.id(),
                Pet.entityFromModel(adoptionModel.pet()),
                Tutor.entityFromModel(adoptionModel.tutor()),
                adoptionModel.date());
    }

    public static Adoption buildNew(Pet pet, Tutor tutor) {
        return new Adoption(
                null,
                pet,
                tutor,
                LocalDateTime.now());
    }
}
