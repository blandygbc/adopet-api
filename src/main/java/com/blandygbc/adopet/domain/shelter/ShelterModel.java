package com.blandygbc.adopet.domain.shelter;

import com.blandygbc.adopet.domain.user.UserModel;

import lombok.Builder;

@Builder
public record ShelterModel(
        Long id,
        String name,
        String image,
        String about,
        String city,
        String state,
        String phone,
        UserModel user) {

}
