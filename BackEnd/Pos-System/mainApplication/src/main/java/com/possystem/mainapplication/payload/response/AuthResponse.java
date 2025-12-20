package com.possystem.mainapplication.payload.response;

import com.possystem.mainapplication.payload.DTO.UserDTO;

// this class is used to return auth  response  when user is logedin
public class AuthResponse {
//    jwt tokem
    private String jwt;
//   message
    private  String message;

//    user dto
    private UserDTO userDTO;

}
