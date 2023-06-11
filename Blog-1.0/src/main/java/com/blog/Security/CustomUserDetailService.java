package com.blog.Security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.blog.Exceptions.ResourceNotFoundException;
import com.blog.Modules.User;
import com.blog.Repositories.UserRepo;

@Service
public class CustomUserDetailService implements UserDetailsService {

	
	@Autowired
	private UserRepo userRepo;
	
//	load user from database by userName 
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		User user = this.userRepo.findByEmail(username).orElseThrow(() -> new ResourceNotFoundException("User", "email :"+username,0 ));
		return user;
	}

}
