package com.blog.Repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import com.blog.Modules.User;
@Component
public interface UserRepo extends JpaRepository<User, Integer>{
	
	Optional<User> findByEmail(String email);

}
