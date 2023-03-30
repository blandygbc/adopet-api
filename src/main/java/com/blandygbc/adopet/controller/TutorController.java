package com.blandygbc.adopet.controller;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.blandygbc.adopet.model.tutor.TutorNewModel;
import com.blandygbc.adopet.tutor.Tutor;
import com.blandygbc.adopet.tutor.TutorRepository;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@RestController
@RequestMapping("tutor")
public class TutorController {
    static Logger logger = Logger.getLogger(TutorController.class.getName());

    @Autowired
    private TutorRepository repository;

    @PostMapping
    @Transactional
    @ResponseStatus(HttpStatus.CREATED)
    public void add(@Valid @RequestBody TutorNewModel newTutor) {
        repository.save(new Tutor(newTutor));
        logger.log(Level.INFO, "Tutor {0} salvo com sucesso!", newTutor.name());
    }
}
