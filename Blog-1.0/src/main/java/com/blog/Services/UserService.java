package com.blog.Services;

import java.util.List;


import com.blog.Payloads.UserDto;

public interface UserService {
	
	public UserDto createUser(UserDto user);

	public UserDto updateUser(UserDto user,Integer id);
	
	public UserDto getById(Integer id);
	
	public List<UserDto> getAllUser();
	
	public void deleteUser(Integer userId);
}
