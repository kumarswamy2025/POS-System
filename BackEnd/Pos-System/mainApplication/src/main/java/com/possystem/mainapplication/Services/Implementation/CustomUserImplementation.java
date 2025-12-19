package com.possystem.mainapplication.Services.Implementation;

import com.possystem.mainapplication.modal.UserModal;
import com.possystem.mainapplication.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;
import java.util.Objects;

@Service
public class CustomUserImplementation implements UserDetailsService {


    @Autowired
    private UserRepo userRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

//        finding user details from DB note: here we using jpa repo
        UserModal userModal = userRepo.findByEmail(username);

        System.out.println("user modal :"+userModal);

        if (Objects.isNull(userModal)) {
            throw new UsernameNotFoundException("user not found.....");
        }

//        simple granted authority class
        GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(userModal.getRole().toString());


        System.out.println("grantedAuthority data: "+grantedAuthority);

//        here we convert granted authorities into list
        Collection<GrantedAuthority> authorities= Collections.singletonList(grantedAuthority);

        System.out.println("authorities data :"+authorities);

//        this is shortcut for implements  UserDetails interface
        return new User(
                userModal.getEmail(),userModal.getPassword(),authorities
        );


    }
}
