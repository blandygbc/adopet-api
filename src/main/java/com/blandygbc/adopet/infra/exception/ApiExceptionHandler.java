package com.blandygbc.adopet.infra.exception;

import java.time.LocalDateTime;
import java.util.List;

import javax.naming.AuthenticationException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.blandygbc.adopet.domain.exception.ApiJWTException;
import com.blandygbc.adopet.domain.exception.CreateJWTException;
import com.blandygbc.adopet.domain.exception.EmptyListException;
import com.blandygbc.adopet.domain.exception.NotAuthorizedException;
import com.blandygbc.adopet.domain.exception.TokenJWTExpiredException;
import com.blandygbc.adopet.util.JsonMessage;

import jakarta.persistence.EntityNotFoundException;

@RestControllerAdvice
public class ApiExceptionHandler {

    private static final String NOT_FOUND_MESSAGE = "Não encontrado";

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity processRuntimeException(RuntimeException ex) {
        if (ex instanceof TokenJWTExpiredException)
            return handleTokenJWTExpired();

        if (ex instanceof ApiJWTException)
            return handleJWTError(ex.getMessage());

        if (ex instanceof CreateJWTException)
            return handleCreateJWTError();

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new InternalErrorDTO(LocalDateTime.now(), ex.getMessage()));
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<JsonMessage> handleError404() {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new JsonMessage(NOT_FOUND_MESSAGE));
    }

    @ExceptionHandler(EmptyListException.class)
    public ResponseEntity<JsonMessage> handleEmptyList() {
        return ResponseEntity.ok().body(new JsonMessage(NOT_FOUND_MESSAGE));
    }

    @ExceptionHandler(NotAuthorizedException.class)
    public ResponseEntity<JsonMessage> handleNotAuthorized() {
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new JsonMessage("Não autorizado, perfil incorreto."));
    }

    @ExceptionHandler(ApiJWTException.class)
    public ResponseEntity<JsonMessage> handleJWTError(String message) {
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new JsonMessage(message));
    }

    @ExceptionHandler(CreateJWTException.class)
    public ResponseEntity<JsonMessage> handleCreateJWTError() {
        return ResponseEntity.status(HttpStatus.FORBIDDEN)
                .body(new JsonMessage("Erro ao criar o token, tente novamente."));
    }

    @ExceptionHandler(TokenJWTExpiredException.class)
    public ResponseEntity<JsonMessage> handleTokenJWTExpired() {
        return ResponseEntity.status(HttpStatus.FORBIDDEN)
                .body(new JsonMessage("Token expirado, faça login novamente."));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleError400(MethodArgumentNotValidException ex) {
        List<FieldError> fieldErrors = ex.getFieldErrors();
        return ResponseEntity.badRequest().body(fieldErrors
                .stream()
                .map(ValidationErrorFields::new)
                .toList());
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<JsonMessage> handleNotReadableException(HttpMessageNotReadableException ex) {
        return ResponseEntity.badRequest().body(new JsonMessage(ex.getMessage()));
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<JsonMessage> handleBadCredentials() {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new JsonMessage("Login ou senha incorreto."));
    }

    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<JsonMessage> handleAuthenticationError() {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new JsonMessage("Falha na autenticação"));
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<JsonMessage> handleAccessDenied() {
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new JsonMessage("Acesso negado"));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<JsonMessage> tratarErro500(Exception ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new JsonMessage("Erro: " + ex.getLocalizedMessage()));
    }

    private record ValidationErrorFields(String field, String message) {
        public ValidationErrorFields(FieldError error) {
            this(error.getField(), error.getDefaultMessage());
        }
    }

    private record InternalErrorDTO(LocalDateTime date, String message) {
    }
}
