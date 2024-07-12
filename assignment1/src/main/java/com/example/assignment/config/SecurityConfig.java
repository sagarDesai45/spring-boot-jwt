package com.example.assignment.config;

import com.example.assignment.jwt.AuthEntryPointJwt;
import com.example.assignment.jwt.JwtAuthTokenFilter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.SecurityBuilder;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.example.assignment.jwt.JwtUtils;


@Configuration
public class SecurityConfig {


    @Autowired
    private AuthEntryPointJwt point;
    @Autowired
    private JwtAuthTokenFilter filter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http.csrf(csrf -> csrf.disable())
                .authorizeRequests().
                requestMatchers("/v1/products").authenticated().requestMatchers("/auth/login").permitAll()
                .anyRequest()
                .authenticated()
                .and().exceptionHandling(ex -> ex.authenticationEntryPoint(point))
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        http.addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }


}


//@Configuration
//public class SecurityConfig {
//
//   
//
//    @Autowired
//    private JwtAuthTokenFilter jwtAuthTokenFilter;
//    
//    @Autowired
//    private AuthEntryPointJwt authEntryPointJwt;
//
//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//    	http.csrf().disable()
//        .authorizeRequests()
//        .requestMatchers("/v1/products/**").hasRole("ADMIN")
//        .requestMatchers("/v1/products/**").hasRole("USER")
//        .anyRequest().authenticated()
//        .and()
//        .exceptionHandling().authenticationEntryPoint(authEntryPointJwt)
//        .and()
//        .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//        .and()
//        .addFilterBefore(jwtAuthTokenFilter, UsernamePasswordAuthenticationFilter.class);
//
//    return http.build();
//    }
//
//   
//}
