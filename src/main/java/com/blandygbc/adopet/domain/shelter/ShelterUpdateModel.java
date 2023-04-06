package com.blandygbc.adopet.domain.shelter;

import jakarta.validation.constraints.NotNull;

public record ShelterUpdateModel(
                @NotNull Long id,
                String name,
                String image,
                String about,
                String city,
                String phone,
                String email,
                String password) {

}
