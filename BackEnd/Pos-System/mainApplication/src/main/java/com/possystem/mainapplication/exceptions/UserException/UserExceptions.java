package com.possystem.mainapplication.exceptions.UserException;

import org.springframework.http.HttpStatus;

public class UserExceptions extends RuntimeException {
    private HttpStatus status;
    public UserExceptions(String message, HttpStatus status) {
        super(message);
        this.status = status;

    }
    public HttpStatus getStatus() {
        return status;
    }
}
