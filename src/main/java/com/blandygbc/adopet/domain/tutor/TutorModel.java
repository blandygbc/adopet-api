package com.blandygbc.adopet.domain.tutor;

import com.blandygbc.adopet.domain.role.RoleModel;

public record TutorModel(
        Long id,
        String name,
        String email,
        String phone,
        String city,
        String state,
        String about,
        String image,
        RoleModel role) {

    public static TutorModel modelFromEntity(Tutor tutor) {
        return new TutorModel(tutor.getId(),
                tutor.getName(),
                tutor.getEmail(),
                tutor.getPhone(),
                tutor.getCity(),
                tutor.getState(),
                tutor.getAbout(),
                tutor.getImage(),
                RoleModel.modelFromEntity(tutor.getRole()));
    }
}
