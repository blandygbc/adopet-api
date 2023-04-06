package com.blandygbc.adopet.domain.shelter;

import com.blandygbc.adopet.domain.role.Role;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity(name = "Shelter")
@Table(name = "shelters")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Shelter {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String image;
    private String about;
    private String city;
    private String phone;
    private String email;
    private String password;
    @ManyToOne
    private Role role;

    public Shelter(ShelterNewModel newShelter, Role role) {
        this.name = newShelter.name();
        this.image = newShelter.image();
        this.about = newShelter.about();
        this.city = newShelter.city();
        this.phone = newShelter.phone();
        this.email = newShelter.email();
        this.password = newShelter.password();
        this.role = role;
    }

    public void updateInfo(@Valid ShelterUpdateModel updateShelter) {
        if (updateShelter.name() != null) {
            this.name = updateShelter.name();
        }
        if (updateShelter.email() != null) {
            this.email = updateShelter.email();
        }
        if (updateShelter.password() != null) {
            this.password = updateShelter.password();
        }
        if (updateShelter.phone() != null) {
            this.phone = updateShelter.phone();
        }
        if (updateShelter.city() != null) {
            this.city = updateShelter.city();
        }
        if (updateShelter.about() != null) {
            this.about = updateShelter.about();
        }
        if (updateShelter.image() != null) {
            this.image = updateShelter.image();
        }
    }
}
