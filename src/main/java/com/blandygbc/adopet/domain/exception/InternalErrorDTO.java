package com.blandygbc.adopet.domain.exception;

import java.time.LocalDateTime;

public record InternalErrorDTO(LocalDateTime date, String message) {
}