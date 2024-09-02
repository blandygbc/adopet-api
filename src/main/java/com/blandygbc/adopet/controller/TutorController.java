package com.blandygbc.adopet.controller;

import java.util.List;

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

import com.blandygbc.adopet.domain.tutor.Tutor;
import com.blandygbc.adopet.domain.tutor.TutorMapper;
import com.blandygbc.adopet.domain.tutor.TutorModel;
import com.blandygbc.adopet.domain.tutor.TutorNewModel;
import com.blandygbc.adopet.domain.tutor.TutorService;
import com.blandygbc.adopet.domain.tutor.TutorUpdateModel;
import com.blandygbc.adopet.util.JsonMessage;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("tutors")
public class TutorController {

    @Autowired
    private final TutorService service;

    @Autowired
    private TutorMapper mapper;

    @PostMapping
    @Transactional
    // @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<TutorModel> add(@Valid @RequestBody TutorNewModel newTutor) {
        Tutor savedTutor = service.createTutor(newTutor);
        return ResponseEntity.ok(mapper.entityToModel(savedTutor));
    }

    @GetMapping
    @SecurityRequirement(name = "bearer-key")
    public ResponseEntity<List<TutorModel>> getAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @PutMapping
    @Transactional
    @SecurityRequirement(name = "bearer-key")
    public ResponseEntity<TutorModel> update(@Valid @RequestBody TutorUpdateModel updateTutor) {
        var tutor = service.updateTutor(updateTutor);
        return ResponseEntity.ok(mapper.entityToModel(tutor));
    }

    @DeleteMapping("/{tutorId}")
    @Transactional
    @SecurityRequirement(name = "bearer-key")
    public ResponseEntity<JsonMessage> delete(@PathVariable Long tutorId) {
        service.deleteTutor(tutorId);
        return ResponseEntity.ok(new JsonMessage("Removido com sucesso!"));
    }

    @GetMapping("/{tutorId}")
    @SecurityRequirement(name = "bearer-key")
    public ResponseEntity<TutorModel> detail(@PathVariable Long tutorId) {
        return ResponseEntity.ok(mapper.entityToModel(
                service.getTutor(tutorId)));
    }

}
