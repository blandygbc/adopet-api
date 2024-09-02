package com.blandygbc.adopet.domain.tutor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.blandygbc.adopet.domain.user.User;
import com.blandygbc.adopet.domain.user.UserMapper;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class TutorMapper {

    @Autowired
    private UserMapper userMapper;

    public Tutor newModelToEntity(String name, User user) {
        return Tutor.builder()
                .name(name)
                .user(user)
                .build();
    }

    public Tutor modelToEntity(TutorModel tutorModel) {
        return Tutor.builder()
                .id(tutorModel.id())
                .name(tutorModel.name())
                .phone(tutorModel.phone())
                .city(tutorModel.city())
                .state(tutorModel.state())
                .about(tutorModel.about())
                .image(tutorModel.image())
                .user(userMapper.modelToEntity(tutorModel.user()))
                .build();
    }

    public TutorModel entityToModel(Tutor tutor) {
        return new TutorModel(tutor.getId(),
                tutor.getName(),
                tutor.getPhone(),
                tutor.getCity(),
                tutor.getState(),
                tutor.getAbout(),
                tutor.getImage(),
                userMapper.entityToModel(tutor.getUser()));
    }
}
