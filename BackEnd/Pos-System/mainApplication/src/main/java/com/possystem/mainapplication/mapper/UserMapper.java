package com.possystem.mainapplication.mapper;

import com.possystem.mainapplication.modal.UserModal;
import com.possystem.mainapplication.payload.DTO.UserDTO;

// here we converting user modal to user dto
//Note: here we not sending password
public class UserMapper {
    public static UserDTO toDTO(UserModal savedUser) {
        UserDTO userDTO=UserDTO.builder()
                .ID(savedUser.getID())
                .fullName(savedUser.getFullName())
                .email(savedUser.getEmail())
                .phone(savedUser.getPhone())
                .role(savedUser.getRole())
                .createdAt(savedUser.getCreatedAt())
                .updatedAt(savedUser.getUpdatedAt())
                .lastLogin(savedUser.getLastLogin())
                .build();
        return userDTO;


    }
}
