package com.possystem.mainapplication.Services;
import com.possystem.mainapplication.exceptions.UserException.UserExceptions;
import com.possystem.mainapplication.payload.DTO.UserDTO;
import com.possystem.mainapplication.payload.response.AuthResponse;

public interface AuthService {

AuthResponse signup(UserDTO userDTO) throws UserExceptions;

AuthResponse login(UserDTO userDTO) throws UserExceptions;

}
