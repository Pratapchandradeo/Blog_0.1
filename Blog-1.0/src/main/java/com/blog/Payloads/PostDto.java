package com.blog.Payloads;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostDto {
	
	private Integer id;
	
	
	private String title;
	
	private String content;
	
	private String image;
	
	private Date addedDate;
	
	private UserDto user;
	
	private CategoryDto category;
	
	private Set<CommentDto> comments = new HashSet<>();

}
