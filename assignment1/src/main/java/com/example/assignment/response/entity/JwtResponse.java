package com.example.assignment.response.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;


@Builder
public class JwtResponse {

	private String username;
	private String jwtToken;
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getJwtToken() {
		return jwtToken;
	}
	public void setJwtToken(String jwtToken) {
		this.jwtToken = jwtToken;
	}
	
	
}
