package com.possystem.mainapplication.exceptions.GlobalException;

import com.possystem.mainapplication.exceptions.UserException.UserExceptions;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(UserExceptions.class)
    public ResponseEntity<?> UserExceptionsHandler(UserExceptions ex) {
        HashMap<String, String> errorResponse = new HashMap<>();
        errorResponse.put("Message", ex.getMessage());
        errorResponse.put("status code", ex.getStatus().toString());
        return new ResponseEntity<>(errorResponse, ex.getStatus());

    }

    @ExceptionHandler(UsernameNotFoundException.class)
    public ResponseEntity<?> UsernameNotFoundExceptionHandler(UsernameNotFoundException ex){
        HashMap<String,String> map=new HashMap<>();
        map.put("message",ex.getMessage());
        map.put("status", HttpStatus.NOT_FOUND.toString());

        return new ResponseEntity<>(map,HttpStatus.NOT_FOUND);
    }


}
