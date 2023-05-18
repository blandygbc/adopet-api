package com.blandygbc.adopet.domain.tutor;

import com.blandygbc.adopet.domain.user.UserModel;

import lombok.Builder;

@Builder
public record TutorModel(
        Long id,
        String name,
        String phone,
        String city,
        String state,
        String about,
        String image,
        UserModel user) {
}
