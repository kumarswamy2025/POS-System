package com.possystem.mainapplication.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

import java.util.ArrayList;

@EnableWebSecurity
@Configuration
public class SecurityConfig {


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {

//        no authentication is required for these apis
        String publicApis[] = {"/test"};


        httpSecurity
//                disable csrf
                .csrf(csrf -> csrf.disable())
//                disable cors
                .cors(cors -> cors.disable())

// creating session management state is stateless , here we use JWT token
                .sessionManagement(management -> management.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
//                no auth is required for publicApis so rest of apis requires auth
                .authorizeHttpRequests(authorise -> authorise
//                           no auth is required for publicApis so rest of apis requires auth
                        .requestMatchers(publicApis).permitAll()



                        .anyRequest().authenticated());


//                default form login
//                .formLogin(Customizer.withDefaults());


        return httpSecurity.build();
    }


}
