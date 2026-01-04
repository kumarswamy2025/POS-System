package com.possystem.mainapplication.exceptions.InventoryException;

import org.springframework.http.HttpStatus;

public class InventoryException extends RuntimeException {
    private HttpStatus httpStatus;
    public InventoryException(String message,HttpStatus httpStatus) {
        super(message);
        this.httpStatus=httpStatus;
    }
    public HttpStatus getStatus() {
        return httpStatus;
    }
}
