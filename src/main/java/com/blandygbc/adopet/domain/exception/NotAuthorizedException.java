package com.blandygbc.adopet.domain.exception;

public class NotAuthorizedException extends ApiException {

    public NotAuthorizedException(String message) {
        super(message);
    }

    public NotAuthorizedException() {
        super("");
    }

}
