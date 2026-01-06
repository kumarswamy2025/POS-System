package com.possystem.mainapplication.exceptions.CustomerException;

import org.springframework.http.HttpStatus;

public class CustomerException extends RuntimeException {
    private HttpStatus httpStatus;
    public CustomerException(String message,HttpStatus httpStatus) {
        super(message);
        this.httpStatus=httpStatus;
    }
    public HttpStatus getStatus() {
        return httpStatus;
    }
}
