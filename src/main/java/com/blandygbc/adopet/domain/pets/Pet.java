package com.blandygbc.adopet.domain.pets;

import org.hibernate.validator.constraints.URL;

import com.blandygbc.adopet.domain.shelter.Shelter;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "pets")
@Entity(name = "Pet")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Pet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String personality;
    private String age;
    @URL
    private String image;
    private PetSpecies species;
    private PetSize size;
    private PetStatus status;
    @ManyToOne
    private Shelter shelter;

    public void updateInfo(PetUpdateModel updatePet) {
        if (updatePet.name() != null) {
            this.name = updatePet.name();
        }
        if (updatePet.personality() != null) {
            this.personality = updatePet.personality();
        }
        if (updatePet.age() != null) {
            this.age = updatePet.age();
        }
        if (updatePet.image() != null) {
            this.image = updatePet.image();
        }
        if (updatePet.size() != null) {
            this.size = updatePet.size();
        }
        if (updatePet.status() != null) {
            this.status = updatePet.status();
        }
    }

    public static Pet entityFromNewModel(PetNewModel newPet, Shelter shelter) {
        return new Pet(
                null,
                newPet.name(),
                newPet.personality(),
                newPet.age(),
                newPet.image(),
                newPet.species(),
                newPet.size(),
                PetStatus.NEW,
                shelter);
    }

    public static Pet entityFromModel(PetModel petModel) {
        return new Pet(
                petModel.id(),
                petModel.name(),
                petModel.personality(),
                petModel.age(),
                petModel.image(),
                petModel.species(),
                petModel.size(),
                petModel.status(),
                Shelter.entityFromModel(petModel.shelter()));
    }

    public void adopt() {
        this.status = PetStatus.ADOPTED;
    }

}
