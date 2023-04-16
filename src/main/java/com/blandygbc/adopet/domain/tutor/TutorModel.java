package com.blandygbc.adopet.domain.tutor;

import com.blandygbc.adopet.domain.user.UserModel;

public record TutorModel(
        Long id,
        String name,
        String phone,
        String city,
        String state,
        String about,
        String image,
        UserModel user) {

    public static TutorModel modelFromEntity(Tutor tutor) {
        return new TutorModel(tutor.getId(),
                tutor.getName(),
                tutor.getPhone(),
                tutor.getCity(),
                tutor.getState(),
                tutor.getAbout(),
                tutor.getImage(),
                UserModel.modelFromEntity(tutor.getUser()));
    }
}
