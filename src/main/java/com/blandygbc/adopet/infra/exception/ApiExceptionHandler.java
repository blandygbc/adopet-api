package com.blandygbc.adopet.infra.exception;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.blandygbc.adopet.domain.exception.EmptyListException;
import com.blandygbc.adopet.domain.exception.Message;

import jakarta.persistence.EntityNotFoundException;

@RestControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<Message> handleError404() {
        return ResponseEntity.status(404).body(new Message("Não encontrado"));
    }

    @ExceptionHandler(EmptyListException.class)
    public ResponseEntity<Message> handleEmptyList() {
        return ResponseEntity.ok().body(new Message("Não encontrado"));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleError400(MethodArgumentNotValidException ex) {
        List<FieldError> fieldErrors = ex.getFieldErrors();
        return ResponseEntity.badRequest().body(fieldErrors
                .stream()
                .map(ValidationErrorFields::new)
                .toList());
    }

    private record ValidationErrorFields(String field, String message) {
        public ValidationErrorFields(FieldError error) {
            this(error.getField(), error.getDefaultMessage());
        }
    }
}
