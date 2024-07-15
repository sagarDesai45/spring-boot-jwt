//package com.example.assignment.serviceImpl;
//
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//
//import com.example.assignment.request.entity.User;
//
//public class CustomUserDetailsService implements UserDetailsService{
//
//	@Override
//	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//		User user=new User();
//		user.setUserName("sagar");
//		user.setPassWord("sagar");
//		user.setRole("ADMIN");
//		
//		return new org.springframework.security.core.userdetails.User("sagar", "sagar", null);
//	}
//
//}
