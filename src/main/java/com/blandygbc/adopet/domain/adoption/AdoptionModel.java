package com.blandygbc.adopet.domain.adoption;

import java.time.LocalDateTime;

import com.blandygbc.adopet.domain.pets.PetModel;
import com.blandygbc.adopet.domain.tutor.TutorModel;

public record AdoptionModel(
        Long id,
        PetModel pet,
        TutorModel tutor,
        LocalDateTime date) {

    public static AdoptionModel modelFromEntity(Adoption savedAdoption) {
        return new AdoptionModel(
                savedAdoption.getId(),
                PetModel.modelFromEntity(savedAdoption.getPet()),
                TutorModel.modelFromEntity(savedAdoption.getTutor()),
                savedAdoption.getDate());
    }

}
