package com.possystem.mainapplication.exceptions.GlobalException;

import com.possystem.mainapplication.exceptions.BranchException.BranchException;
import com.possystem.mainapplication.exceptions.CategoryException.CategoryException;
import com.possystem.mainapplication.exceptions.EmployeException.EmployeeException;
import com.possystem.mainapplication.exceptions.InventoryException.InventoryException;
import com.possystem.mainapplication.exceptions.ProductException.ProductException;
import com.possystem.mainapplication.exceptions.StoreException.StoreException;
import com.possystem.mainapplication.exceptions.UserException.UserExceptions;
import jakarta.validation.constraints.Email;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
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

    @ExceptionHandler(BadCredentialsException.class)
    public  ResponseEntity<?> BadCredentialsExceptionHandler(BadCredentialsException ex){

        HashMap<String,String> map=new HashMap<>();
        map.put("message",ex.getMessage());
        map.put("status", HttpStatus.NOT_FOUND.toString());
        return new ResponseEntity<>(map,HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(StoreException.class)
    public ResponseEntity<?> StoreExceptionHandler(StoreException ex){
        HashMap<String,String> map=new HashMap<>();

        map.put("message",ex.getMessage());
        map.put("status", ex.getStatus().toString());
        return new ResponseEntity<>(map,ex.getStatus());
    }

    @ExceptionHandler(ProductException.class)
    public ResponseEntity<?> ProductExceptionHandler(ProductException ex){
        HashMap<String,String> map=new HashMap<>();

        map.put("message",ex.getMessage());
        map.put("status", ex.getStatus().toString());
        return new ResponseEntity<>(map,ex.getStatus());
    }
    @ExceptionHandler(CategoryException.class)
    public ResponseEntity<?> CategoryExceptionHandler(CategoryException ex){
        HashMap<String,String> map=new HashMap<>();

        map.put("message",ex.getMessage());
        map.put("status", ex.getStatus().toString());
        return new ResponseEntity<>(map,ex.getStatus());
    }
    @ExceptionHandler(BranchException.class)
    public ResponseEntity<?> BranchExceptionHandler(BranchException ex){
        HashMap<String,String> map=new HashMap<>();

        map.put("message",ex.getMessage());
        map.put("status", ex.getStatus().toString());
        return new ResponseEntity<>(map,ex.getStatus());
    }
    @ExceptionHandler(InventoryException.class)
    public ResponseEntity<?> InventoryExceptionHandler(InventoryException ex){
        HashMap<String,String> map=new HashMap<>();

        map.put("message",ex.getMessage());
        map.put("status", ex.getStatus().toString());
        return new ResponseEntity<>(map,ex.getStatus());
    }
    @ExceptionHandler(EmployeeException.class)
    public ResponseEntity<?> EmployeeExceptionHandler(EmployeeException ex){
        HashMap<String,String> map=new HashMap<>();

        map.put("message",ex.getMessage());
        map.put("status", ex.getStatus().toString());
        return new ResponseEntity<>(map,ex.getStatus());
    }
}
