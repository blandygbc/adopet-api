package com.blandygbc.adopet.utils;

import com.blandygbc.adopet.domain.tutor.Tutor;
import com.blandygbc.adopet.domain.tutor.TutorModel;
import com.blandygbc.adopet.domain.tutor.TutorNewModel;
import static com.blandygbc.adopet.utils.UserUtils.*;

public abstract class TutorUtils {
    public static TutorNewModel getCompleteTutorNewModel() {
        return TutorNewModel.builder()
                .name("Jessica")
                .email("jessica@email.com")
                .password("123456")
                .build();
    }

    public static TutorNewModel getTutorNewModelWithEmail(String email) {
        return TutorNewModel.builder()
                .name("Jessica")
                .email(email)
                .password("123456")
                .build();
    }

    public static TutorNewModel getTutorNewModelWithPassword(String password) {
        return TutorNewModel.builder()
                .name("Jessica")
                .email("jessica@email.com")
                .password(password)
                .build();
    }

    public static TutorNewModel getTutorNewModelWithName(String name) {
        return TutorNewModel.builder()
                .name(name)
                .email("jessica@email.com")
                .password("123456")
                .build();
    }

    public static TutorModel getTutorModelSavedNew() {
        return TutorModel.builder()
                .id(1l)
                .name("Jessica")
                .user(getUserModel())
                .build();
    }

    public static Tutor getTutorSavedNew() {
        return Tutor.builder()
                .id(1l)
                .name("Jessica")
                .user(getUser())
                .build();
    }

    public static Tutor getTutorComplete() {
        return Tutor.builder()
                .id(1l)
                .name("Jessica")
                .image("http://some.url.com/image")
                .phone("21999999999")
                .city("Rio de janeiro")
                .state("RJ")
                .about("Some nice tutor")
                .user(getUser())
                .build();
    }
}
