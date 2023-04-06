package com.blandygbc.adopet.domain.tutor;

import com.blandygbc.adopet.domain.role.Role;

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
    private String email;
    private String password;
    private String phone;
    private String city;
    private String about;
    private String image;
    @ManyToOne
    private Role role;

    public Tutor(TutorNewModel newTutor, Role role) {
        this.name = newTutor.name();
        this.email = newTutor.email();
        this.password = newTutor.password();
        this.role = role;
    }

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
        if (updateTutor.about() != null) {
            this.about = updateTutor.about();
        }
        if (updateTutor.image() != null) {
            this.image = updateTutor.image();
        }
    }

}
