package com.possystem.mainapplication.controller;

import com.possystem.mainapplication.Service.Services.UserService;
import com.possystem.mainapplication.mapper.UserMapper;
import com.possystem.mainapplication.modal.UserModal;
import com.possystem.mainapplication.payload.DTO.UserDTO;
import com.possystem.mainapplication.payload.DTO.UserProfileDTO;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/kumar")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final ModelMapper modelMapper;

    @GetMapping("/profile")
    public ResponseEntity<UserProfileDTO> getUserProfile(@RequestHeader("Authorization") String JwtToken) {

        System.out.println("jwtToken:"+JwtToken);
        UserModal user = userService.getUserFromJWTToken(JwtToken);
        UserProfileDTO result = modelMapper.map(user, UserProfileDTO.class);
        System.out.println("this is user profile DTO:" + result);
        return ResponseEntity.ok(result);

    }

    @GetMapping("/{id}")
    public ResponseEntity<UserProfileDTO> getUserById(@PathVariable("id") Long id) {

        UserModal user = userService.getUserById(id);
        UserProfileDTO result = modelMapper.map(user, UserProfileDTO.class);
        System.out.println("this is user profile DTO:" + result);
        return ResponseEntity.ok(result);

    }


}
