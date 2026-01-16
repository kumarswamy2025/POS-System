package com.possystem.mainapplication.exceptions.RefundException;

import org.springframework.http.HttpStatus;

public class RefundException extends RuntimeException {
    private HttpStatus status;
    public RefundException(String message, HttpStatus status) {
        super(message);
        this.status = status;
    }

    public HttpStatus getStatus() {
        return status;
    }
}
