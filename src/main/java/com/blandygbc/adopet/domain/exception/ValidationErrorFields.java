package com.blandygbc.adopet.domain.exception;

import org.springframework.validation.FieldError;

public record ValidationErrorFields(String field, String message) {
    public ValidationErrorFields(FieldError error) {
        this(error.getField(), error.getDefaultMessage());
    }
}
