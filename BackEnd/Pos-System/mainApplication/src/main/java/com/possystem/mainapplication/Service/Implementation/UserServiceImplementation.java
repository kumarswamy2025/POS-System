package com.possystem.mainapplication.Service.Implementation;

import com.possystem.mainapplication.Service.Services.UserService;
import com.possystem.mainapplication.configuration.jwtConfig.JwtService;
import com.possystem.mainapplication.exceptions.UserException.UserExceptions;
import com.possystem.mainapplication.modal.UserModal;
import com.possystem.mainapplication.repository.UserRepo;
import jakarta.validation.constraints.Null;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
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
//        we extract email from token
        String Email = jwtService.getEmailFromToken(token);
//        finding user details from DB using repo
        UserModal userData = userRepo.findByEmail(Email);
//        checking if user is not found...

        if (userData == null) {
            throw new UserExceptions("Invalid token ", HttpStatus.BAD_REQUEST);
        }
        return userData;
    }

    @Override
    public UserModal getCurrentUser() {
//        here we get user data from security context holder
        String Email = SecurityContextHolder.getContext().getAuthentication().getName();

        UserModal userData = userRepo.findByEmail(Email);
        if (userData == null) {
            throw new UserExceptions("User not found.... ", HttpStatus.BAD_REQUEST);
        }
        return userData;
    }

    @Override
    public UserModal getUserByEmail(String Email) {

        UserModal userData = userRepo.findByEmail(Email);
        if (userData == null) {
            throw new UserExceptions("User not found.... ", HttpStatus.BAD_REQUEST);
        }
        return userData;
    }

    @Override
    public UserModal getUserById(Long Id) {
        return userRepo.findById(Id).orElse(null);

    }

    @Override
    public List<UserModal> getAllUsers() {
        return userRepo.findAll();
    }
}
