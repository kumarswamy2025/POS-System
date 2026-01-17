package com.possystem.mainapplication.exceptions.ShiftReportException;

import org.springframework.http.HttpStatus;

public class ShirtReportException extends RuntimeException {
    private HttpStatus status;
    public ShirtReportException(String message, HttpStatus status) {
        super(message);
        this.status = status;
    }

    public HttpStatus getStatus() {
        return status;
    }
}
