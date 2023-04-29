package com.blandygbc.adopet.utils;

import com.blandygbc.adopet.domain.user.User;
import com.blandygbc.adopet.domain.user.UserModel;

import static com.blandygbc.adopet.utils.RoleUtils.*;

public abstract class UserUtils {
    public static User getUser() {
        return User.builder()
                .id(1l)
                .email("jessica@email.com")
                .role(getTutorRole())
                .build();
    }

    public static UserModel getUserModel() {
        return UserModel.builder()
                .id(1l)
                .email("jessica@email.com")
                .role(getTutorRoleModel())
                .build();
    }
}
