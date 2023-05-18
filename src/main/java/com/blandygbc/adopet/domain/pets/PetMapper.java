package com.blandygbc.adopet.domain.pets;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.blandygbc.adopet.domain.shelter.Shelter;
import com.blandygbc.adopet.domain.shelter.ShelterMapper;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class PetMapper {

    @Autowired
    private ShelterMapper shelterMapper;

    public Pet newModelToEntity(PetNewModel newPet, Shelter shelter) {
        return Pet.builder()
                .id(null)
                .name(newPet.name())
                .personality(newPet.personality())
                .age(newPet.age())
                .image(newPet.image())
                .species(newPet.species())
                .size(newPet.size())
                .status(PetStatus.NEW)
                .shelter(shelter)
                .build();
    }

    public Pet modelToEntity(PetModel petModel) {
        return Pet.builder()
                .id(petModel.id())
                .name(petModel.name())
                .personality(petModel.personality())
                .age(petModel.age())
                .image(petModel.image())
                .species(petModel.species())
                .size(petModel.size())
                .status(petModel.status())
                .shelter(shelterMapper.modelToEntity(petModel.shelter()))
                .build();
    }

    public PetModel entityToModel(Pet pet) {
        return PetModel.builder()
                .id(pet.getId())
                .name(pet.getName())
                .personality(pet.getPersonality())
                .age(pet.getAge())
                .image(pet.getImage())
                .species(pet.getSpecies())
                .size(pet.getSize())
                .status(pet.getStatus())
                .shelter(shelterMapper.entityToModel(pet.getShelter()))
                .build();
    }
}
