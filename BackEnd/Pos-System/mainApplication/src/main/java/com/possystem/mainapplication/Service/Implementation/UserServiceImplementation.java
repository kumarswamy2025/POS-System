package com.possystem.mainapplication.Service.Implementation;

import com.possystem.mainapplication.Service.Services.UserService;
import com.possystem.mainapplication.modal.UserModal;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
public class UserServiceImplementation implements UserService {
    @Override
    public UserModal getUserFromJWTToken(String token) {


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
