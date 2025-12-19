package com.possystem.mainapplication.configuration.jwtConfig;

import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.security.PrivateKey;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Service
public class JwtProvider {

    /*
     * This class is used to generate token when new user logins
     *
     * */

//    autowired JwtService bean
//    @Autowired
    private  JwtService jwtService;

    //        generate secreate key

   private SecretKey key;
//   here we using constractor  dependency injection
    public JwtProvider(JwtService jwtService) {
        this.jwtService = jwtService;
        this.key = jwtService.generateKey(); // ✅ SAFE
    }

    public  String  generateToken(Authentication authentication){



//        we get authorities
        Collection<? extends GrantedAuthority> authorities=authentication.getAuthorities();

        String roles=populateAuthorities(authorities);

        return Jwts.builder()
                .issuedAt(new Date())
                .expiration(new Date(new Date().getTime()+8640000))
                .claim("email",authentication.getName())
                .claim("authorities",roles)
                .compact()
                ;


    }

//    this method returs set of roles
    private String populateAuthorities(Collection<? extends GrantedAuthority> authorities) {

        Set<String> auths=new HashSet<>();
        for (GrantedAuthority authority:authorities){
            auths.add(authority.getAuthority());
        }
        return String.join(",",auths);
    }


}
