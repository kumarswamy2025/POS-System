package com.possystem.mainapplication.Service.Implementation;

import com.possystem.mainapplication.Service.Services.UserService;
import com.possystem.mainapplication.configuration.jwtConfig.JwtService;
import com.possystem.mainapplication.modal.UserModal;
import com.possystem.mainapplication.repository.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
public class UserServiceImplementation implements UserService {

//    here we inject dependencies NOTE: here we using @RequiredArgsConstructor annotations so no need to use constractor for dependency injection
    private final UserRepo userRepo;
    private final JwtService jwtService;


    @Override
    public UserModal getUserFromJWTToken(String token) {

        String Email=jwtService.getEmailFromToken(token);



        return null;
    }

    @Override
    public UserModal getCurrentUser() {
        return null;
    }

    @Override
    public UserModal getUserByEmail(String Email) {
        return null;
    }

    @Override
    public UserModal getUserById(Long Id) {
        return null;
    }

    @Override
    public List<UserModal> getAllUsers() {
        return List.of();
    }
}
