package com.example.assignment.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.example.assignment.dao.entity.UserInfo;
import java.util.List;



@Repository
public interface UserRepository extends JpaRepository<UserInfo,Integer>{
	
	@Query("SELECT U FROM UserInfo U WHERE U.username=:username")
	public UserInfo findUser(@Param("username") String userName); 
}
