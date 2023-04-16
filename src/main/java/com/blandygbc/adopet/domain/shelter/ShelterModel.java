package com.blandygbc.adopet.domain.shelter;

import com.blandygbc.adopet.domain.user.UserModel;

public record ShelterModel(
        Long id,
        String name,
        String image,
        String about,
        String city,
        String state,
        String phone,
        UserModel user) {

    public static ShelterModel modelFromEntity(Shelter shelter) {
        return new ShelterModel(
                shelter.getId(),
                shelter.getName(),
                shelter.getImage(),
                shelter.getAbout(),
                shelter.getCity(),
                shelter.getState(),
                shelter.getPhone(),
                UserModel.modelFromEntity(shelter.getUser()));
    }

}
