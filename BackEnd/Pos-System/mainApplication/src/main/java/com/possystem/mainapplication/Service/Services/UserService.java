package com.possystem.mainapplication.Service.Services;

import com.possystem.mainapplication.modal.UserModal;

import java.util.List;

public interface UserService {
    UserModal getUserFromJWTToken(String token);
    UserModal getCurrentUser();
    UserModal getUserByEmail(String Email);
    UserModal getUserById(Long Id);
    List<UserModal> getAllUsers();

}
