package com.blandygbc.adopet.domain.tutor;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.blandygbc.adopet.domain.exception.EmptyListException;
import com.blandygbc.adopet.domain.role.BasicRoles;
import com.blandygbc.adopet.domain.user.AuthService;
import com.blandygbc.adopet.domain.user.User;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class TutorService {
    private TutorRepository repository;
    private AuthService authService;

    public Tutor createTutor(TutorNewModel newTutor) {
        User user = authService.createUser(newTutor.email(), newTutor.password(), BasicRoles.TUTOR.getId());
        Tutor savedTutor = repository.save(Tutor.entityFromNewModel(newTutor.name(), user));
        log.info("successfully created tutor with id: {}", savedTutor.getId());
        return savedTutor;
    }

    public Tutor getTutor(Long tutorId) {
        return repository.getReferenceById(tutorId);
    }

    public List<TutorModel> findAll() {
        List<TutorModel> tutors = repository.findAll().stream()
                .map(TutorModel::modelFromEntity)
                .collect(Collectors.toList());
        if (tutors.isEmpty()) {
            log.info("No registered tutor yet");
            throw new EmptyListException();
        }
        return tutors;
    }

    public Tutor updateTutor(TutorUpdateModel updateTutor) {
        var tutor = repository.getReferenceById(updateTutor.id());
        tutor.updateInfo(updateTutor);
        authService.updateUser(tutor.getUser(), updateTutor.email(), updateTutor.password());
        return tutor;
    }

    public void deleteTutor(Long tutorId) {
        Integer result = repository.deleteTutorById(tutorId);
        if (result == 0) {
            throw new EntityNotFoundException();
        }
    }
}
