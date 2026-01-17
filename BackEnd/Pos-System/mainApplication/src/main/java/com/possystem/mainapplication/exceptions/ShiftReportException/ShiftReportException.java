package com.possystem.mainapplication.exceptions.ShiftReportException;

import org.springframework.http.HttpStatus;

public class ShiftReportException extends RuntimeException {
    private HttpStatus status;
    public ShiftReportException(String message, HttpStatus status) {
        super(message);
        this.status = status;
    }

    public HttpStatus getStatus() {
        return status;
    }
}
