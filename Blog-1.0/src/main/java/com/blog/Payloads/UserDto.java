package com.blog.Payloads;


import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
	

	private Integer id;
	
	
	@NotEmpty(message="Username must not be empty")
	@Size(min=5,message="Username must be min of 5 character ")
	private String name;
	
	@NotEmpty
	@Email(message="Email address is not valid !!")
	private String email;
	
//	Password must contain at least one digit [0-9].
//	Password must contain at least one lowercase Latin character [a-z].
//	Password must contain at least one uppercase Latin character [A-Z].
//	Password must contain at least one special character like ! @ # & ( ).
//	Password must contain a length of at least 4 characters and a maximum of 10 characters.
	@JsonIgnore
	@NotEmpty
	@Size(min=4,max=10,message="password must be min 4 character and maximum 10 character !!")
	@Pattern(regexp ="^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()–[{}]:;',?/*~$^+=<>]).{4,10}$",message="Password must contain number,lowercase,Uppercase,spacial character")
	private String password;
	
	@NotNull
	private String about;
	

}
