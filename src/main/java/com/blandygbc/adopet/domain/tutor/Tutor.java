package com.blandygbc.adopet.domain.tutor;

import org.hibernate.validator.constraints.URL;

import com.blandygbc.adopet.domain.role.Role;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "tutors")
@Entity(name = "Tutor")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Tutor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @Email
    private String email;
    private String password;
    private String phone;
    private String city;
    private String state;
    private String about;
    @URL
    private String image;
    @ManyToOne
    private Role role;

    public void updateInfo(TutorUpdateModel updateTutor) {
        if (updateTutor.name() != null) {
            this.name = updateTutor.name();
        }
        if (updateTutor.email() != null) {
            this.email = updateTutor.email();
        }
        if (updateTutor.password() != null) {
            this.password = updateTutor.password();
        }
        if (updateTutor.phone() != null) {
            this.phone = updateTutor.phone();
        }
        if (updateTutor.city() != null) {
            this.city = updateTutor.city();
        }
        if (updateTutor.state() != null) {
            this.state = updateTutor.state();
        }
        if (updateTutor.about() != null) {
            this.about = updateTutor.about();
        }
        if (updateTutor.image() != null) {
            this.image = updateTutor.image();
        }
    }

    public static Tutor entityFromNewModel(TutorNewModel newTutor, Role role) {
        return new Tutor(
                null,
                newTutor.name(),
                newTutor.email(),
                newTutor.password(),
                null,
                null,
                null,
                null,
                null,
                role);
    }

    public static Tutor entityFromModel(TutorModel tutorModel) {
        return new Tutor(
                tutorModel.id(),
                tutorModel.name(),
                tutorModel.email(),
                null,
                tutorModel.phone(),
                tutorModel.city(),
                tutorModel.state(),
                tutorModel.about(),
                tutorModel.image(),
                Role.entityFromModel(tutorModel.role()));
    }

}
