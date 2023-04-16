package com.blandygbc.adopet.domain.exception;

public class TokenJWTExpiredException extends ApiException {

    public TokenJWTExpiredException(String message) {
        super(message);
    }

    public TokenJWTExpiredException() {
        super("");
    }

}
