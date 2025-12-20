package com.possystem.mainapplication.Services.Implementation;

import com.possystem.mainapplication.Services.AuthService;
import com.possystem.mainapplication.configuration.jwtConfig.JwtProvider;
import com.possystem.mainapplication.exceptions.UserExceptions;
import com.possystem.mainapplication.modal.UserModal;
import com.possystem.mainapplication.payload.DTO.UserDTO;
import com.possystem.mainapplication.payload.response.AuthResponse;
import com.possystem.mainapplication.repository.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

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
       UserModal userModal= userRepo.findByEmail(userDTO.getEmail());

       if(userModal!=null){
           throw new UserExceptions("emial is already exits..");
       }



        return null;
    }

    @Override
    public AuthResponse login(UserDTO userDTO) {
        return null;
    }
}
