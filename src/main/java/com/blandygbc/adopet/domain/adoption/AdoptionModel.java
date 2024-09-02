package com.blandygbc.adopet.domain.adoption;

import java.time.LocalDateTime;

import com.blandygbc.adopet.domain.pets.PetModel;
import com.blandygbc.adopet.domain.tutor.TutorModel;

import lombok.Builder;

@Builder
public record AdoptionModel(
        Long id,
        PetModel pet,
        TutorModel tutor,
        LocalDateTime date) {

}
