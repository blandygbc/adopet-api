package com.blandygbc.adopet.domain.model.tutor;

import com.blandygbc.adopet.domain.tutor.Tutor;

public record TutorModel(
        Long id,
        String name,
        String email) {

    public TutorModel(Tutor tutor) {
        this(tutor.getId(),
                tutor.getName(),
                tutor.getEmail());
    }
}
