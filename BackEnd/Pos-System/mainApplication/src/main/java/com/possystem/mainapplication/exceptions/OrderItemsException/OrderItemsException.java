package com.possystem.mainapplication.exceptions.OrderItemsException;

import org.springframework.http.HttpStatus;

public class OrderItemsException extends RuntimeException {
    private HttpStatus httpStatus;
    public OrderItemsException(String message,HttpStatus httpStatus) {
        super(message);
        this.httpStatus=httpStatus;
    }
    public HttpStatus getStatus() {
        return httpStatus;
    }
}
