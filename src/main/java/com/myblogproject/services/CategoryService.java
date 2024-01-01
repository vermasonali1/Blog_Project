package com.myblogproject.services;

import java.util.List;

import com.myblogproject.entities.Category;
import com.myblogproject.payloads.CategoryDto;

public interface CategoryService {

	
//	create-----
	public CategoryDto createCategory(CategoryDto categoryDto);

	//	update------
	public CategoryDto updateCategory(CategoryDto categoryDto, Integer categoryId);

//	delete-----
	public void deleteCategory(Integer categoryId);

//	get----------
	public CategoryDto getCategory(Integer categoryId);

//	getall-----
	List<CategoryDto> getCategories();
}
