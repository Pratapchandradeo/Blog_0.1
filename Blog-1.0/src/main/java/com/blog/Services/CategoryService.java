package com.blog.Services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.blog.Payloads.CategoryDto;

@Service
public interface CategoryService {
	
	public CategoryDto cresteCategory(CategoryDto categoryDto);
	
	
	public CategoryDto update(CategoryDto categoryDto, Integer id);
	
//	get single Category
	public CategoryDto getById(Integer id);
	
//	get all Category
	public List<CategoryDto> getAllCategory();
	
	public void deleteCategory(Integer id);

}
