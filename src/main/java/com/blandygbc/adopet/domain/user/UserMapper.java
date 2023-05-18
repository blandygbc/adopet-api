package com.blandygbc.adopet.domain.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.blandygbc.adopet.domain.role.RoleMapper;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class UserMapper {
    @Autowired
    private final RoleMapper roleMapper;

    public User modelToEntity(UserModel userModel) {
        return User.builder()
                .id(userModel.id())
                .email(userModel.email())
                .role(roleMapper.modelToEntity(userModel.role()))
                .build();
    }

    public UserModel entityToModel(User user) {
        return UserModel.builder()
                .id(user.getId())
                .email(user.getEmail())
                .role(roleMapper.entityToModel(user.getRole()))
                .build();
    }

}
