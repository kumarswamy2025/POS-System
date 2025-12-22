package com.possystem.mainapplication.Services.Implementation;

import com.possystem.mainapplication.Services.AuthService;
import com.possystem.mainapplication.configuration.jwtConfig.JwtProvider;
import com.possystem.mainapplication.domain.UserRole;
import com.possystem.mainapplication.exceptions.UserException.UserExceptions;
import com.possystem.mainapplication.mapper.UserMapper;
import com.possystem.mainapplication.modal.UserModal;
import com.possystem.mainapplication.payload.DTO.UserDTO;
import com.possystem.mainapplication.payload.response.AuthResponse;
import com.possystem.mainapplication.repository.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Collection;

@Service
@RequiredArgsConstructor // this annotation is used to implement constractor injection dependency  Note: if we want contractor injection means we have to use final or @NonNull fields
public class AuthServiceImplementation implements AuthService {

    @Autowired
    private UserRepo userRepo;  // here automatically injects required dependency it can be done by lombok


    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JwtProvider jwtProvider;
    @Autowired
    private CustomUserImplementation customUserImplementation;


    //    this method is used to register new user
    @Override
    public AuthResponse signup(UserDTO userDTO) throws UserExceptions {
//        here we check if email is already exits or not
        UserModal checkingEmail = userRepo.findByEmail(userDTO.getEmail());
        if (checkingEmail != null) {
            throw new UserExceptions("email is already exits..", HttpStatus.CONFLICT);
        }

//        here we checking if user ready to create ROLE_ADMIN so we have to throw an exception
        if (userDTO.getRole().equals(UserRole.ROLE_ADMIN)) {
            throw new UserExceptions("role admin is not allowed...",HttpStatus.FORBIDDEN);
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

        String jwtToken = jwtProvider.generateToken(authentication);

//        here we return auth response
        AuthResponse authResponse = AuthResponse.builder().jwt(jwtToken).message("Registered successfully...").userDTO(
//                        here we converting user modal to user dto
                UserMapper.toDTO(savedUser)).build();


        return authResponse;
    }

    @Override
    public AuthResponse login(UserDTO userDTO) throws UserExceptions {
//        getting email FROM user dto
        String email = userDTO.getEmail();
//        gettig password from user DTO
        String password = userDTO.getPassword();

//        here we get authentication object
        Authentication authentication = authentication(email, password);

        System.out.println("this is authentication object in loged :"+authentication);
//        here we save authentication object in SecurityContextHolder
        SecurityContextHolder.getContext().setAuthentication(authentication);

//        getting authorities
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();

//         getting role ofmlogedin user
        String role = authorities.iterator().next().getAuthority();

//        generate jwt token
        String jwtToken = jwtProvider.generateToken(authentication);

//        fetching user data from repo
        UserModal userModal = userRepo.findByEmail(email);

//        update last logedin time
        userModal.setLastLogin(LocalDateTime.now());

//        save the user
        userRepo.save(userModal);

//        here we return auth response

        AuthResponse authResponse = AuthResponse.builder().jwt(jwtToken).message("LogedIn successfully....").userDTO(
//                        here we converting user modal to user dto
                UserMapper.toDTO(userModal)).build();


        return authResponse;
    }

//    this method will check user exits or not
//    check password is valid or not
//    return authentication object

    private Authentication authentication(String email, String password) throws UserExceptions {

//        fetch user details from
        UserDetails userDetails = customUserImplementation.loadUserByUsername(email);
//        checking if user is exits or not
        if (userDetails == null) {
            throw new UserExceptions("please enter valid Email...",HttpStatus.BAD_REQUEST);
        }

//        checking if user password is correct or not
        if (!passwordEncoder.matches(password, userDetails.getPassword())) {

            throw new UserExceptions("invalid password....",HttpStatus.BAD_REQUEST);
        }


//        here we return authentication object to save in SecurityContextHolder
        return new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
    }
}
