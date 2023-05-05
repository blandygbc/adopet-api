package com.blandygbc.adopet.domain.tutor;

import org.hibernate.validator.constraints.URL;

import com.blandygbc.adopet.domain.user.User;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "tutors")
@Entity(name = "Tutor")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(of = "id")
public class Tutor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    @Size(min = 2, max = 100)
    private String name;
    @Size(min = 10, max = 11) // 21 99999 9999
    private String phone;
    private String city;
    private String state;
    private String about;
    @URL
    private String image;
    @OneToOne(cascade = { CascadeType.REMOVE, CascadeType.PERSIST }, optional = false, fetch = FetchType.LAZY)
    private User user;

    public Tutor(String name, User user) {
        this.name = name;
        this.user = user;
    }

    public void updateInfo(TutorUpdateModel updateTutor) {
        if (updateTutor.name() != null
                && !updateTutor.name().isBlank()) {
            this.name = updateTutor.name();
        }
        if (updateTutor.phone() != null) {
            this.phone = updateTutor.phone();
        }
        if (updateTutor.city() != null
                && !updateTutor.city().isBlank()) {
            this.city = updateTutor.city();
        }
        if (updateTutor.state() != null
                && !updateTutor.state().isBlank()) {
            this.state = updateTutor.state();
        }
        if (updateTutor.about() != null
                && !updateTutor.about().isBlank()) {
            this.about = updateTutor.about();
        }
        if (updateTutor.image() != null
                && !updateTutor.image().isBlank()) {
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
