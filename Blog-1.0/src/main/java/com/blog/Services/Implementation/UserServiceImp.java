package com.blog.Services.Implementation;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blog.Exceptions.ResourceNotFoundException;
import com.blog.Modules.User;
import com.blog.Payloads.UserDto;
import com.blog.Repositories.UserRepo;
import com.blog.Services.UserService;

@Service
public class UserServiceImp implements UserService {
	
	@Autowired
	private UserRepo userRepo;

	@Autowired
	private ModelMapper modelMapper;
	
	@Override
	public UserDto createUser(UserDto user) {
		User user1 = this.UserDtoToUSer(user);
		User saveUser = userRepo.save(user1);
		return this.UserToUserDto(saveUser);
	}

	@Override
	public UserDto updateUser(UserDto user, Integer id) {
					User user1 =  userRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("User","id",id));
					user1.setName(user.getName());
					user1.setEmail(user.getEmail());
					user1.setPassword(user.getPassword());
					user1.setAbout(user.getAbout());
					
					
					User user2 = userRepo.save(user1);
					
					
					
		return this.UserToUserDto(user2);
	}

	@Override
	public UserDto getById(Integer id) {
		User user = userRepo.findById(id).orElseThrow(()-> new ResourceNotFoundException("User", "id", id));
		
		return this.UserToUserDto(user);
	}

	@Override
	public List<UserDto> getAllUser() {
		List<User> user =userRepo.findAll();
		
		List<UserDto>	user2 =	user.stream().map(user1 -> this.UserToUserDto(user1)).collect(Collectors.toList());
		return user2;
	}

	@Override
	public void deleteUser(Integer userId) {
		User user =   userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("user","id",userId));
				userRepo.delete(user);
		
	}
	
	private User UserDtoToUSer(UserDto user)
	{
		User user1 = this.modelMapper.map(user, User.class);
		
//		user1.setId(user.getId());
//		user1.setEmail(user.getEmail());
//		user1.setName(user.getName());
//		user1.setPassword(user.getPassword());
//		user1.setAbout(user.getAbout());
		
		return user1;
	}
	
	private UserDto UserToUserDto(User user)
	{
		//Useing ModelMapper
		UserDto user1 = this.modelMapper.map(user, UserDto.class);
		
		//manualy 
//		user1.setId(user.getId());
//		user1.setEmail(user.getEmail());
//		user1.setName(user.getName());
//		user1.setPassword(user.getPassword());
//		user1.setAbout(user.getAbout());
		
		return user1;
	}

}
