package com.example.assignment.dao.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="roles")
public class Roles {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="role_id")
	private int id;
	
	private String role;
	
//	@ManyToMany(mappedBy="roles")
//	@JsonBackReference
//	private List<UserInfo> user;
 	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

//	public List<UserInfo> getUser() {
//		return user;
//	}
//
//	public void setUser(List<UserInfo> user) {
//		this.user = user;
//	}

	
	
	
}
