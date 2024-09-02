package com.blandygbc.adopet.domain.adoption;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.blandygbc.adopet.domain.pets.PetMapper;
import com.blandygbc.adopet.domain.tutor.TutorMapper;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class AdoptionMapper {

    @Autowired
    private PetMapper petMapper;
    @Autowired
    private TutorMapper tutorMapper;

    public Adoption modelToEntity(AdoptionModel adoptionModel) {
        return Adoption.builder()
                .id(adoptionModel.id())
                .pet(petMapper.modelToEntity(adoptionModel.pet()))
                .tutor(tutorMapper.modelToEntity(adoptionModel.tutor()))
                .date(adoptionModel.date())
                .build();
    }

    public AdoptionModel entityToModel(Adoption adoption) {
        return AdoptionModel.builder()
                .id(adoption.getId())
                .pet(petMapper.entityToModel(adoption.getPet()))
                .tutor(tutorMapper.entityToModel(adoption.getTutor()))
                .date(adoption.getDate())
                .build();
    }

}
