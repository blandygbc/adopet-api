package com.blandygbc.adopet.domain.exception;

public class EmptyListException extends ApiException {

    public EmptyListException(String message) {
        super(message);
    }

    public EmptyListException() {
    }

}
