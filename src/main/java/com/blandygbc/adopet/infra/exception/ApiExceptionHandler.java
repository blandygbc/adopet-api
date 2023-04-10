package com.blandygbc.adopet.infra.exception;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.blandygbc.adopet.domain.exception.EmptyListException;
import com.blandygbc.adopet.domain.exception.NotAuthorizedException;
import com.blandygbc.adopet.util.JsonMessage;

import jakarta.persistence.EntityNotFoundException;

@RestControllerAdvice
public class ApiExceptionHandler {

    private static final String NOT_FOUND_MESSAGE = "Não encontrado";

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<JsonMessage> handleError404() {
        return ResponseEntity.status(404).body(new JsonMessage(NOT_FOUND_MESSAGE));
    }

    @ExceptionHandler(EmptyListException.class)
    public ResponseEntity<JsonMessage> handleEmptyList() {
        return ResponseEntity.ok().body(new JsonMessage(NOT_FOUND_MESSAGE));
    }

    @ExceptionHandler(NotAuthorizedException.class)
    public ResponseEntity<JsonMessage> handleNotAuthorized() {
        return ResponseEntity.status(403).body(new JsonMessage("Não autorizado, perfil incorreto."));
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
