package com.possystem.mainapplication.controller;

import com.possystem.mainapplication.Service.Services.AuthService;
import com.possystem.mainapplication.exceptions.UserException.UserExceptions;
import com.possystem.mainapplication.payload.DTO.UserDTO;
import com.possystem.mainapplication.payload.response.AuthResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private  final AuthService authService;

//    http:localhost:8080/auth/signup

    @PostMapping("/signup")
    public ResponseEntity<AuthResponse> signupHandler(@RequestBody UserDTO userDTO) throws UserExceptions {

        return ResponseEntity.ok(
                authService.signup(userDTO)
        );

    }
//    http:localhost:8080/auth/login

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> loginHandler(@RequestBody UserDTO userDTO) throws UserExceptions {

        return ResponseEntity.ok(
                authService.login(userDTO)
        );

    }





}
