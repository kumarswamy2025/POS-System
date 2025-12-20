package com.possystem.mainapplication.Services.Implementation;

import com.possystem.mainapplication.Services.AuthService;
import com.possystem.mainapplication.configuration.jwtConfig.JwtProvider;
import com.possystem.mainapplication.domain.UserRole;
import com.possystem.mainapplication.exceptions.UserExceptions;
import com.possystem.mainapplication.mapper.UserMapper;
import com.possystem.mainapplication.modal.UserModal;
import com.possystem.mainapplication.payload.DTO.UserDTO;
import com.possystem.mainapplication.payload.response.AuthResponse;
import com.possystem.mainapplication.repository.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor // this annotation is used to implement constractor injection dependency
public class AuthServiceImplementation implements AuthService {

    private UserRepo userRepo;  // here automatically injects required dependency it can be done by lombok


    private PasswordEncoder passwordEncoder;

    private JwtProvider jwtProvider;

    private CustomUserImplementation customUserImplementation;


    //    this method is used to register new user
    @Override
    public AuthResponse signup(UserDTO userDTO) throws UserExceptions {
//        here we check if email is already exits or not
        UserModal checkingEmail = userRepo.findByEmail(userDTO.getEmail());
        if (checkingEmail != null) {
            throw new UserExceptions("email is already exits..");
        }

//        here we checking if user ready to create ROLE_ADMIN so we have to throw an exception
        if (userDTO.getRole().equals(UserRole.ROLE_ADMIN)) {
            throw new UserExceptions("role admin is not allowed...");
        }

//        creating new user
// here iam using builder patterns to make objects simple
        UserModal newUser = UserModal.builder().fullName(userDTO.getFullName()).email(userDTO.getEmail()).phone(userDTO.getPhone()).role(userDTO.getRole()).password(passwordEncoder.encode(userDTO.getPassword())).createdAt(LocalDateTime.now()).updatedAt(LocalDateTime.now()).lastLogin(LocalDateTime.now()).build();

//        save user
        UserModal savedUser = userRepo.save(newUser);

//        creating authentication object
        Authentication authentication = new UsernamePasswordAuthenticationToken(userDTO.getEmail(), userDTO.getPassword());
// save authetication object in, to security context holder
        SecurityContextHolder.getContext().setAuthentication(authentication);

//        here we generate jwt token for who newly register

        String jwtToken=jwtProvider.generateToken(authentication);

//        here we return auth response
        AuthResponse authResponse=AuthResponse.builder()
                .jwt(jwtToken)
                .message("Registered successfully...")
                .userDTO(
//                        here we converting user modal to user dto
                        UserMapper.toDTO(savedUser))
                .build();





        return authResponse;
    }

    @Override
    public AuthResponse login(UserDTO userDTO) {
        return null;
    }
}
