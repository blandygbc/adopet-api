package com.blandygbc.adopet.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blandygbc.adopet.domain.exception.EmptyListException;
import com.blandygbc.adopet.domain.exception.Message;
import com.blandygbc.adopet.domain.model.tutor.TutorModel;
import com.blandygbc.adopet.domain.model.tutor.TutorNewModel;
import com.blandygbc.adopet.domain.model.tutor.TutorUpdateModel;
import com.blandygbc.adopet.domain.tutor.Tutor;
import com.blandygbc.adopet.domain.tutor.TutorRepository;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@RestController
@RequestMapping("tutores")
public class TutorController {

    @Autowired
    private TutorRepository repository;

    @PostMapping
    @Transactional
    // @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<TutorModel> add(@Valid @RequestBody TutorNewModel newTutor) {
        Tutor savedTutor = repository.save(new Tutor(newTutor));
        return ResponseEntity.ok(new TutorModel(savedTutor));
    }

    @GetMapping
    public ResponseEntity<List<TutorModel>> getAll() {
        List<TutorModel> tutors = repository.findAll().stream()
                .map(TutorModel::new)
                .collect(Collectors.toList());
        if (tutors.isEmpty()) {
            throw new EmptyListException();
        }
        return ResponseEntity.ok(tutors);
    }

    @PutMapping
    @Transactional
    public ResponseEntity<TutorModel> update(@Valid @RequestBody TutorUpdateModel updateTutor) {
        var tutor = repository.getReferenceById(updateTutor.id());
        tutor.updateInfo(updateTutor);
        return ResponseEntity.ok(new TutorModel(tutor));
    }

    @DeleteMapping("/{tutorId}")
    @Transactional
    public ResponseEntity<Message> delete(@PathVariable Long tutorId) {
        Integer result = repository.deleteTutorById(tutorId);
        if (result == 0) {
            throw new EntityNotFoundException();
        }
        return ResponseEntity.ok(new Message("Removido com sucesso!"));
    }

    @GetMapping(value = "/{tutorId}")
    public ResponseEntity<TutorModel> detail(@PathVariable Long tutorId) {
        Tutor tutor = repository.getReferenceById(tutorId);
        return ResponseEntity.ok(new TutorModel(tutor));
    }

}
