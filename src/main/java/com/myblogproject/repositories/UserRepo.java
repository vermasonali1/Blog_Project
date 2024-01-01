package com.myblogproject.repositories;


import java.util.Optional;

//import org.springframework.boot.autoconfigure.security.SecurityProperties.User;
import org.springframework.data.jpa.repository.JpaRepository;

import com.myblogproject.entities.User;

public interface UserRepo extends JpaRepository<User,Integer> {

	Optional<User>findByEmail(String email);
	
}



