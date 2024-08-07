package com.example.assignment.config;

import com.example.assignment.jwt.AuthEntryPointJwt;
import com.example.assignment.jwt.JwtAuthTokenFilter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.SecurityBuilder;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.example.assignment.jwt.JwtUtils;
import com.example.assignment.serviceImpl.CustomUserDetailsService;


@Configuration
@EnableWebSecurity
public class SecurityConfig  {


    @Autowired
    private AuthEntryPointJwt point;
    @Autowired
    private JwtAuthTokenFilter filter;
    
    @Autowired
    private  CustomUserDetailsService customUserDetailsService;

   
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http.csrf(csrf -> csrf.disable())
        .authorizeRequests()
        .requestMatchers("/swagger-ui/**", "/v3/api-docs/**","/auth/**").permitAll()
        .requestMatchers(HttpMethod.GET, "/v1/products/product/**").hasAnyRole("USER", "ADMIN")
        .requestMatchers(HttpMethod.POST, "/v1/products/product/**").hasRole("ADMIN")
        .requestMatchers("/v1/products/insert-product/**").hasRole("ADMIN")
        .requestMatchers(HttpMethod.PUT, "/v1/products/product/**").hasRole("ADMIN")
        .requestMatchers(HttpMethod.DELETE, "/v1/products/product/**").hasRole("ADMIN")
        .anyRequest().authenticated()
        .and().sessionManagement()
        .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class);
        http.authenticationProvider(daoAuthenticationProvider());
        return http.build();
    }
    
   @Bean
   public AuthenticationManager authenticationManagerBean(AuthenticationConfiguration configuration)throws Exception{
	   return configuration.getAuthenticationManager();
   }
   
   @Bean
   public DaoAuthenticationProvider daoAuthenticationProvider() {
	   DaoAuthenticationProvider provider=new DaoAuthenticationProvider();
	   provider.setUserDetailsService(this.customUserDetailsService);
	   provider.setPasswordEncoder(passwordEncoder());
	   return provider;
   }
    
//    @Bean
//    public AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
//        return http.getSharedObject(AuthenticationManagerBuilder.class)
//                .userDetailsService(customUserDetailsService)
//                .passwordEncoder(passwordEncoder())
//                .and()
//                .build();
//    }
//    
    

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(); // Use BCryptPasswordEncoder for password hashing
    }

}