package com.blandygbc.adopet.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.blandygbc.adopet.domain.exception.EmptyListException;
import com.blandygbc.adopet.domain.model.tutor.TutorModel;
import com.blandygbc.adopet.domain.model.tutor.TutorNewModel;
import com.blandygbc.adopet.domain.tutor.Tutor;
import com.blandygbc.adopet.domain.tutor.TutorRepository;

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

}
