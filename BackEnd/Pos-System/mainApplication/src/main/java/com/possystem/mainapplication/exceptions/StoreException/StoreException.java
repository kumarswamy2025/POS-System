package com.possystem.mainapplication.exceptions.StoreException;

import org.springframework.http.HttpStatus;

public class StoreException extends RuntimeException {
    private HttpStatus status;
    public StoreException(String message, HttpStatus status) {
        super(message);
        this.status = status;
    }

    public HttpStatus getStatus() {
        return status;
    }
}
