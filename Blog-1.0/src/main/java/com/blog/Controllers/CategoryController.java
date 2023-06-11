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
import com.blog.Payloads.CategoryDto;
import com.blog.Services.CategoryService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {
	
	@Autowired
	private CategoryService categoryService;
	
	@PostMapping("/")
	public ResponseEntity<CategoryDto> createCategory(@Valid  @RequestBody CategoryDto categoryDto){
		CategoryDto categoryDto2 =   this.categoryService.cresteCategory(categoryDto);
		
		return new ResponseEntity<CategoryDto>(categoryDto2,HttpStatus.CREATED);
	}

	@PutMapping("/{id}")
	public ResponseEntity<CategoryDto> updateCategory(@Valid @RequestBody CategoryDto categoryDto , @PathVariable("id") Integer id){
		
		CategoryDto categoryDto2 =		this.categoryService.update(categoryDto, id);
		return new ResponseEntity<CategoryDto>(categoryDto2,HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<ApiResponse> deleteCategory(@PathVariable("id") Integer id){
		this.categoryService.deleteCategory(id);
		
		return new ResponseEntity<ApiResponse>(new ApiResponse("Category Deleted Successfully !!",true),HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<CategoryDto> getCategory(@PathVariable("id") Integer id){
		
	CategoryDto categoryDto =	this.categoryService.getById(id);
	
	return new ResponseEntity<CategoryDto>(categoryDto,HttpStatus.OK);
		
	}
	
	@GetMapping("/")
	public ResponseEntity<List<CategoryDto>> getAllCategory()
	{
		List<CategoryDto> li =	this.categoryService.getAllCategory();
		
		return new ResponseEntity<List<CategoryDto>>(li,HttpStatus.OK);
	}
}
