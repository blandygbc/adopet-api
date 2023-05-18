package com.blandygbc.adopet.domain.pets;

import org.hibernate.validator.constraints.URL;

import com.blandygbc.adopet.domain.shelter.Shelter;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "pets")
@Entity(name = "Pet")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
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
    @Enumerated(EnumType.STRING)
    private PetSpecies species;
    @Enumerated(EnumType.STRING)
    private PetSize size;
    @Enumerated(EnumType.STRING)
    private PetStatus status;
    @ManyToOne(fetch = FetchType.LAZY)
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

    public void adopt() {
        this.status = PetStatus.ADOPTED;
    }

    public void setPetAvailable() {
        this.status = PetStatus.AVAILABLE;
    }

}
