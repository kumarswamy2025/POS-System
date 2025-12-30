package com.possystem.mainapplication.exceptions.CategoryException;

import org.springframework.http.HttpStatus;

public class CategoryException extends RuntimeException{
    private HttpStatus httpStatus;
    public CategoryException(String message,HttpStatus httpStatus) {
        super(message);
        this.httpStatus=httpStatus;
    }
    public HttpStatus getStatus() {
        return httpStatus;
    }
}
