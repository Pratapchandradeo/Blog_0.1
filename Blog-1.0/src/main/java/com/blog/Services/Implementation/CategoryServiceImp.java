package com.blog.Services.Implementation;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blog.Exceptions.ResourceNotFoundException;
import com.blog.Modules.Category;
import com.blog.Payloads.CategoryDto;
import com.blog.Repositories.CategoryRepo;
import com.blog.Services.CategoryService;

@Service
public class CategoryServiceImp implements CategoryService{
	
	@Autowired
	private CategoryRepo categoryRepo;
	
	@Autowired
	private ModelMapper modelMapper;

	@Override
	public CategoryDto cresteCategory(CategoryDto categoryDto) {
		
		Category category = this.modelMapper.map(categoryDto, Category.class);
		 Category saveData =this.categoryRepo.save(category);
		 CategoryDto categoryDto2 = this.modelMapper.map(saveData, CategoryDto.class);
		return categoryDto2;
	}

	@Override
	public CategoryDto update(CategoryDto categoryDto, Integer id) {
		Category category = this.categoryRepo.findById(id).orElseThrow(()->new ResourceNotFoundException("Category", "id", id));
		 category.setCategoryTitle(categoryDto.getCategoryTitle());
		 category.setCategoryDescription(categoryDto.getCategoryDescription());
		 	Category saveDeta = this.categoryRepo.save(category);
		 
		
		return this.modelMapper.map(saveDeta, CategoryDto.class);
	}

	@Override
	public CategoryDto getById(Integer id) {
		Category category = this.categoryRepo.findById(id).orElseThrow(() ->new ResourceNotFoundException("Category", "Id", id));
		
		
		return this.modelMapper.map(category, CategoryDto.class);
	}

	@Override
	public List<CategoryDto> getAllCategory() {
		List<Category> categories = this.categoryRepo.findAll();
		List<CategoryDto> listCategory =categories.stream().map((c) -> this.modelMapper.map(c, CategoryDto.class)).collect(Collectors.toList());
		return listCategory;
	}

	@Override
	public void deleteCategory(Integer id) {
		Category category = this.categoryRepo.findById(id).orElseThrow(()-> new ResourceNotFoundException("Category", "id", id));
				this.categoryRepo.delete(category);
		
	}

}
