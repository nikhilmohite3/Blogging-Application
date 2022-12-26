package com.blogging.app.services;

import java.util.List;

import com.blogging.app.payloads.CategoryDTO;

public interface CategoryService {
	CategoryDTO createCategory(CategoryDTO categoryDTO);

	CategoryDTO udpateCategory(CategoryDTO categoryDTO, int id);

	void deleteCategory(int id);

	CategoryDTO getCategory(int id);

	List<CategoryDTO> getCategories();
}
