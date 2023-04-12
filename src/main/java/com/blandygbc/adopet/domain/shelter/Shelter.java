package com.blandygbc.adopet.domain.shelter;

import org.hibernate.validator.constraints.URL;

import com.blandygbc.adopet.domain.role.Role;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
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
    @URL
    private String image;
    private String about;
    private String city;
    private String state;
    private String phone;
    @Email
    private String email;
    private String password;
    @ManyToOne
    private Role role;

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
        if (updateShelter.state() != null) {
            this.state = updateShelter.state();
        }
        if (updateShelter.about() != null) {
            this.about = updateShelter.about();
        }
        if (updateShelter.image() != null) {
            this.image = updateShelter.image();
        }
    }

    public static Shelter entityFromNewModel(ShelterNewModel shelterModel, Role role) {
        return new Shelter(
                null,
                shelterModel.name(),
                shelterModel.image(),
                shelterModel.about(),
                shelterModel.city(),
                shelterModel.state(),
                shelterModel.phone(),
                shelterModel.email(),
                shelterModel.password(),
                role);
    }

    public static Shelter entityFromModel(ShelterModel shelterModel) {
        return new Shelter(
                shelterModel.id(),
                shelterModel.name(),
                shelterModel.image(),
                shelterModel.about(),
                shelterModel.city(),
                shelterModel.state(),
                shelterModel.phone(),
                shelterModel.email(),
                shelterModel.password(),
                Role.entityFromModel(shelterModel.role()));
    }

}
