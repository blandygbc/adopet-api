package com.blandygbc.adopet.domain.model.tutor;

import com.blandygbc.adopet.domain.model.role.RoleModel;
import com.blandygbc.adopet.domain.tutor.Tutor;

public record TutorModel(
        Long id,
        String name,
        String email,
        Integer phone,
        String city,
        String about,
        String image,
        RoleModel role) {

    public TutorModel(Tutor tutor) {
        this(tutor.getId(),
                tutor.getName(),
                tutor.getEmail(),
                tutor.getPhone(),
                tutor.getCity(),
                tutor.getAbout(),
                tutor.getImage(),
                new RoleModel(tutor.getRole()));
    }
}
