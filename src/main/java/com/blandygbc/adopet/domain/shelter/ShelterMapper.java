package com.blandygbc.adopet.domain.shelter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.blandygbc.adopet.domain.user.User;
import com.blandygbc.adopet.domain.user.UserMapper;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class ShelterMapper {
    @Autowired
    private UserMapper userMapper;

    public Shelter newModelToEntity(ShelterNewModel shelterNewModel, User user) {
        return Shelter.builder()
                .name(shelterNewModel.name())
                .image(shelterNewModel.image())
                .about(shelterNewModel.about())
                .city(shelterNewModel.city())
                .state(shelterNewModel.state())
                .phone(shelterNewModel.phone())
                .user(user)
                .build();
    }

    public Shelter modelToEntity(ShelterModel shelterModel) {
        return Shelter.builder()
                .name(shelterModel.name())
                .image(shelterModel.image())
                .about(shelterModel.about())
                .city(shelterModel.city())
                .state(shelterModel.state())
                .phone(shelterModel.phone())
                .user(userMapper.modelToEntity(shelterModel.user()))
                .build();
    }

    public ShelterModel entityToModel(Shelter shelter) {
        return ShelterModel.builder()
                .id(shelter.getId())
                .name(shelter.getName())
                .image(shelter.getImage())
                .about(shelter.getAbout())
                .city(shelter.getCity())
                .state(shelter.getState())
                .phone(shelter.getPhone())
                .user(userMapper.entityToModel(shelter.getUser()))
                .build();
    }
}
