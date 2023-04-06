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

    public TutorModel(Tutor tutor) {
        this(tutor.getId(),
                tutor.getName(),
                tutor.getEmail(),
                tutor.getPhone(),
                tutor.getCity(),
                tutor.getState(),
                tutor.getAbout(),
                tutor.getImage(),
                new RoleModel(tutor.getRole()));
    }
}
