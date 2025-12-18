package com.possystem.mainapplication.configuration.jwtConfig;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

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

//                secreate key










            } catch (Exception e) {

            }
        }


    }
}
