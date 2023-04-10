package com.blandygbc.adopet.domain.shelter;

import com.blandygbc.adopet.domain.role.RoleModel;

public record ShelterModel(
        Long id,
        String name,
        String image,
        String about,
        String city,
        String state,
        String phone,
        String email,
        String password,
        RoleModel role) {

    public static ShelterModel modelFromEntity(Shelter shelter) {
        return new ShelterModel(
                shelter.getId(),
                shelter.getName(),
                shelter.getImage(),
                shelter.getAbout(),
                shelter.getCity(),
                shelter.getState(),
                shelter.getPhone(),
                shelter.getEmail(),
                shelter.getPassword(),
                RoleModel.modelFromEntity(shelter.getRole()));
    }

}
