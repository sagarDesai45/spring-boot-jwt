package com.example.assignment.controller;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.assignment.dao.entity.Roles;
import com.example.assignment.dao.entity.UserInfo;
import com.example.assignment.jwt.JwtUtils;
import com.example.assignment.repo.UserRepository;
import com.example.assignment.request.entity.User;
import com.example.assignment.response.entity.JwtResponse;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/auth")
public class LoginController {

	
	 	@Autowired
	    private UserDetailsService userDetailsService;

	    @Autowired
	    private AuthenticationManager manager;
	    
	    @Autowired
	    private UserRepository userRepository;

	    @Autowired
	    private PasswordEncoder passwordEncoder;
	    
	    @Autowired
	    private JwtUtils jwtUtils;
	    
	    @PostMapping("/login")
	    public ResponseEntity<JwtResponse> login(@RequestBody User user)
	    {
	    	this.doAuthenticate(user.getUserName(), user.getPassWord());
	    	
	    	UserDetails userDetails = userDetailsService.loadUserByUsername(user.getUserName());
	        String token = this.jwtUtils.generateToken(userDetails);

	        JwtResponse response = new JwtResponse();
	        response.setJwtToken(token);
	        response.setUsername(userDetails.getUsername());
	               
	        return new ResponseEntity<>(response, HttpStatus.OK);
	    }
	    
	    @PostMapping("/signup")
	    public UserInfo signup(@Valid @RequestBody UserInfo user)
	    {
//	    	return user;
//	    	user.setPassword(passwordEncoder.encode(user.getPassword()));
	    	return userRepository.save(user);
	    	
	    }
	    
	    
	    private void doAuthenticate(String name, String password) {
	    	
	        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(name, password);
	        try {
	            manager.authenticate(authentication);


	        } catch (BadCredentialsException e) {
	            throw new BadCredentialsException(" Invalid Username or Password  !!");
	        }

	    }
}
