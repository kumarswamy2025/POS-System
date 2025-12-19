package com.possystem.mainapplication.configuration.jwtConfig;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.crypto.SecretKey;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;

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
                System.out.println("Generated sereate key:" + key);

//                creating claims
                Claims claims = Jwts.parser().verifyWith(key).build().parseSignedClaims(jwt).getPayload();

                System.out.println("claims play load:" + claims);

//                we extracting email from  claims
                String email = String.valueOf(claims.get("email"));

//                we get authorites
                String authorities = String.valueOf(claims.get("authorities"));

//                here we convert authorites in comma separated values as list
                List<GrantedAuthority> auths = AuthorityUtils.commaSeparatedStringToAuthorityList(authorities);

                System.out.println("auths result: " + auths);

//                here we validate email
                Authentication auth = new UsernamePasswordAuthenticationToken(email, null, auths);

                System.out.println("security context before inisalization :" + SecurityContextHolder.getContext());

//                we save auth result in security context holder
                SecurityContextHolder.getContext().setAuthentication(auth);

//                printing secuirty context
                System.out.println("security context after inisalization :" + SecurityContextHolder.getContext());


            } catch (Exception e) {
                throw new BadCredentialsException("Invalid JWT.....");


            }
        }

        filterChain.doFilter(request,response);


    }


}
