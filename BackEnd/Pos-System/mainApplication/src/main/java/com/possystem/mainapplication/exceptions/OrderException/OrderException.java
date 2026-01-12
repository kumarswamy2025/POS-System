package com.possystem.mainapplication.exceptions.OrderException;

import org.springframework.http.HttpStatus;

public class OrderException extends RuntimeException {
    private HttpStatus httpStatus;
    public OrderException(String message,HttpStatus httpStatus) {
        super(message);
        this.httpStatus=httpStatus;
    }
    public HttpStatus getStatus() {
        return httpStatus;
    }
}
