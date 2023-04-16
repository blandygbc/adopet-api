package com.blandygbc.adopet.domain.exception;

public class ApiException extends RuntimeException {

    public ApiException(String message) {
        super(message);
    }

    public ApiException(String string, Exception exception) {
        super(string, exception);
    }

}
