package com.possystem.mainapplication.configuration.jwtConfig;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;

@Service
public class JwtService {

    //               generate secreate key
//    note : this is seecreat  key same for every request
//    and jwt token is different for every request because playload chanegs,time changes and uses many factors to change jwt token
    public SecretKey generateKey(){
        return Keys.hmacShaKeyFor(JwtConstant.JWT_SECRET.getBytes(StandardCharsets.UTF_8));
    }

//   extract mail from toke
    public  String getEmailFromToken(String jwtToken){

        if (jwtToken == null || jwtToken.isBlank()) {
            throw new IllegalArgumentException("JWT token is null or empty");
        }

        // ✅ Strip Bearer if someone passes header by mistake
        if (jwtToken.startsWith("Bearer ")) {
            jwtToken = jwtToken.substring(7);
        }

        jwtToken = jwtToken.trim(); // 🔥 VERY IMPORTANT

        Claims claims = Jwts.parser()
                .verifyWith(generateKey())
                .build()
                .parseSignedClaims(jwtToken)
                .getPayload();

        return String.valueOf(claims.get("email"));


    }



}
