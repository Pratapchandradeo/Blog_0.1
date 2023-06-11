package com.blog.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blog.Payloads.ApiResponse;
import com.blog.Payloads.UserDto;
import com.blog.Services.Implementation.UserServiceImp;

import jakarta.validation.Valid;



@RestController
@RequestMapping("/api/user")
public class UserController {
	
	@Autowired
	private UserServiceImp userServiceImp;
	
	
	// post - create user
	@PostMapping("/")
	public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto userDto){
		
		UserDto userdto = userServiceImp.createUser(userDto);
		return new ResponseEntity<UserDto>(userdto,HttpStatus.OK);
		
	}
	
	
	//put - update user
	
	@PutMapping("/{id}")
	public ResponseEntity<UserDto> updateUser( @Valid @RequestBody UserDto userDto,@PathVariable("id") Integer id)
	{
		UserDto user	=	userServiceImp.updateUser(userDto, id);
		
		return new ResponseEntity<UserDto>(user,HttpStatus.OK);
		
	}
	
	
	//get - get user
	@GetMapping("/{id}")
	public ResponseEntity<UserDto> getUserById(@PathVariable("id") Integer id){
		
		UserDto user = userServiceImp.getById(id);
		
		
		return new ResponseEntity<UserDto>(user,HttpStatus.OK);
	}
	
	@GetMapping("/")
	public ResponseEntity<List<UserDto>> getAllUser(){
		
		List<UserDto> user =			this.userServiceImp.getAllUser();
		
		return new ResponseEntity<List<UserDto>>(user,HttpStatus.OK);
		
	}
	
	//delete - delete user
	@DeleteMapping("/{id}")
	public ResponseEntity<ApiResponse> userDelete(@PathVariable("id") Integer id){
		
		this.userServiceImp.deleteUser(id);
		
		return new ResponseEntity<ApiResponse>(new ApiResponse("User Deleted Successfully", true),HttpStatus.OK);
		
		
		
	}

}
