package com.possystem.mainapplication.configuration;

import jakarta.servlet.http.HttpServletRequest;
import org.jspecify.annotations.Nullable;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

@EnableWebSecurity
@Configuration
public class SecurityConfig {


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {

//        no authentication is required for these apis
        String publicApis[] = {"/test"};


        String adminUrls[] = {"/api/**"};
        String superAdminUrls[] = {"/api/super-admin/**"};


        httpSecurity


// creating session management state is stateless , here we use JWT token
                .sessionManagement(management -> management.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
//                no auth is required for publicApis so rest of apis requires auth suppose if we use  .anyRequest().permitAll()
                .authorizeHttpRequests(authorise -> authorise
//                           no auth is required for publicApis so rest of apis requires auth
                        .requestMatchers(publicApis).permitAll()

//                        to access admin urls we have to authenticate
                        .requestMatchers(adminUrls).authenticated()
//                        to access this urls user must have a role admin and must be authneticated
                        .requestMatchers(superAdminUrls).hasRole("ADMIN")

//                        rest of urls no need to authenticate
                        .anyRequest().permitAll())
//  add filter
                .addFilterBefore(new JwtValidator(), BasicAuthenticationFilter.class)
                // disable csrf
                .csrf(csrf -> csrf.disable())
//                disable cors
                .cors(cors -> cors.configurationSource(corsConfigurationMethod()));

//                default form login
//                .formLogin(Customizer.withDefaults());


        return httpSecurity.build();
    }

    //    here we configure cors and we allowed required origin domain urls
    private CorsConfigurationSource corsConfigurationMethod() {

//        return  new CorsConfigurationSource() {
//            @Override
//            public @Nullable CorsConfiguration getCorsConfiguration(HttpServletRequest request) {
//                return null;
//            }
//        };

        CorsConfigurationSource AllowedDomains = new CorsConfigurationSource() {
            //            annominous function without functional interface
            @Override
            public @Nullable CorsConfiguration getCorsConfiguration(HttpServletRequest request) {

// here we configure cors
                CorsConfiguration cfg = new CorsConfiguration();
                cfg.setAllowedOrigins(
                        // here we add frontend urls and third party urls
                        Arrays.asList("http://localhost:5173/", "http://localhost:3000/"));
//                here we set  allowed methods like fetch,post,put,delete
                cfg.setAllowedMethods(Collections.singletonList("*")); // we allowed all methods
//                here we allowed credemtials
                cfg.setAllowCredentials(true);
// here we set  headers
                cfg.setAllowedHeaders(Collections.singletonList("*"));
//                here we expose headers
                cfg.setExposedHeaders(Arrays.asList("Authorization"));

                cfg.setMaxAge(3600L);

                return cfg;
            }
        };


        return AllowedDomains;


    }


}
