package com.example.assignment.serviceImpl;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.assignment.dao.entity.UserInfo;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import com.example.assignment.repo.UserRepository;
import com.example.assignment.request.entity.CustomUserDetails;

@Service
public class CustomUserDetailsService implements UserDetailsService{

	@Autowired
	private UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		UserInfo user=userRepository.findUser(username);
		if(user==null)
		{
			throw new UsernameNotFoundException("User Not Found");
		}
		
		return new CustomUserDetails(
				user.getUsername(),
				user.getPassword(),
				user.getRole());
		
	}
	
//	private Collection<SimpleGrantedAuthority> mapRolesToAuthorities(List<String> roles) {
//        return roles.stream()
//            .map(role -> new SimpleGrantedAuthority(role))
//            .collect(Collectors.toList());
//    }

}
