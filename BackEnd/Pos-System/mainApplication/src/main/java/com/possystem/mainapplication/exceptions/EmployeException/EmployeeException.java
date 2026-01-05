package com.possystem.mainapplication.exceptions.EmployeException;

import org.springframework.http.HttpStatus;

public class EmployeeException extends RuntimeException {
    private HttpStatus httpStatus;
    public EmployeeException(String message,HttpStatus httpStatus) {
        super(message);
        this.httpStatus=httpStatus;
    }
    public HttpStatus getStatus() {
        return httpStatus;
    }
}
