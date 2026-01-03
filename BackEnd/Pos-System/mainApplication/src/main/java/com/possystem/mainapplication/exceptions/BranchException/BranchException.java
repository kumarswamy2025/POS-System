package com.possystem.mainapplication.exceptions.BranchException;

import org.springframework.http.HttpStatus;

public class BranchException extends RuntimeException{
    private HttpStatus httpStatus;
    public BranchException(String message,HttpStatus httpStatus) {
        super(message);
        this.httpStatus=httpStatus;
    }
    public HttpStatus getStatus() {
        return httpStatus;
    }
}
