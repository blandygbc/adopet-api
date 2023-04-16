package com.blandygbc.adopet.domain.tutor;

import org.hibernate.validator.constraints.URL;

import com.blandygbc.adopet.domain.user.User;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
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
    private String phone;
    private String city;
    private String state;
    private String about;
    @URL
    private String image;
    @OneToOne
    private User user;

    public Tutor(String name, User user) {
        this.name = name;
        this.user = user;
    }

    public void updateInfo(TutorUpdateModel updateTutor) {
        if (updateTutor.name() != null) {
            this.name = updateTutor.name();
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

    public static Tutor entityFromNewModel(String name, User user) {
        return new Tutor(name, user);
    }

    public static Tutor entityFromModel(TutorModel tutorModel) {
        return new Tutor(
                tutorModel.id(),
                tutorModel.name(),
                tutorModel.phone(),
                tutorModel.city(),
                tutorModel.state(),
                tutorModel.about(),
                tutorModel.image(),
                User.entityFromModel(tutorModel.user()));
    }

}
