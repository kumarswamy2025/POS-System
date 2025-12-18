package com.possystem.mainapplication.configuration.jwtConfig;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.crypto.SecretKey;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

@Component
public class JwtValidator extends OncePerRequestFilter {
    /*
     * This class is used to check token and if token is valid then it returns future filters authentication is success
     *
     * */

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String jwt = request.getHeader(JwtConstant.JWT_HEADER);

        //       checking if header  and header starts with Bearer or not
        if (jwt == null || !jwt.startsWith("Bearer")) {
            jwt = jwt.substring(7);
            try {

//               generate secreate key
//    note : this is seecreat  key same for every request
//    and jwt token is different for every request because playload chanegs,time changes and uses many factors to change jwt token

                SecretKey key = Keys.hmacShaKeyFor(JwtConstant.JWT_SECRET.getBytes(StandardCharsets.UTF_8));

//                creating claims
                Claims claims = Jwts.parser()
                        .verifyWith(key)
                        .build()
                        .parseSignedClaims(jwt)
                        .getPayload();

//                we extracting email from  claims
                String email=(String) claims.get("email");

            } catch (Exception e) {

            }
        }


    }


}
