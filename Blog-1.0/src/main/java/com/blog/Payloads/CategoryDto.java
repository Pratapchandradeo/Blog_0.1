package com.blog.Payloads;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class CategoryDto {
	
	private Integer cateId;
	
	@NotEmpty(message = "Title can't be empty")
	@Size(min = 4,message = "Title must be more then 4 charatcer")
	private String categoryTitle;
	
	@NotEmpty(message = "Description Can't be empty")
	@Size(min =20, message = "Description must be 20 character")
	private String categoryDescription;
	

}
