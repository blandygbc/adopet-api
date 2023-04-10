package com.blandygbc.adopet.domain.adoption;

import jakarta.validation.constraints.NotNull;

public record AdoptionNewModel(
        @NotNull Long pet,
        @NotNull Long tutor) {

}
