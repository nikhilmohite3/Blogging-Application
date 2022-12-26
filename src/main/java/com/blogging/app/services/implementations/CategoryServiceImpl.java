package com.blogging.app.services.implementations;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.blogging.app.entities.Category;
import com.blogging.app.exceptions.ResourceNotFoundException;
import com.blogging.app.payloads.CategoryDTO;
import com.blogging.app.repositories.CategoryRepository;
import com.blogging.app.services.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService {

	private CategoryRepository repository;
	private ModelMapper modelMapper;

	public CategoryServiceImpl(CategoryRepository repository, ModelMapper modelMapper) {
		this.repository = repository;
		this.modelMapper = modelMapper;
	}

	@Override
	public CategoryDTO createCategory(CategoryDTO categoryDTO) {

		Category newCategory = repository.save(modelMapper.map(categoryDTO, Category.class));
		return modelMapper.map(newCategory, CategoryDTO.class);

	}

	@Override
	public CategoryDTO udpateCategory(CategoryDTO categoryDTO, int id) {
		Category existingCategory = repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Category with given ID Not Found"));
		existingCategory.setCategoryDescription(categoryDTO.getCategoryDescription());
		existingCategory.setCategoryTitle(categoryDTO.getCategoryTitle());

		Category udpatedCategory = repository.save(existingCategory);

		return modelMapper.map(udpatedCategory, CategoryDTO.class);
	}

	@Override
	public void deleteCategory(int id) {
		// TODO Auto-generated method stub
		Category category = repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Category with given ID Not Found"));
		repository.delete(category);
	}

	@Override
	public CategoryDTO getCategory(int id) {
		// TODO Auto-generated method stub
		Category category = repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Category with given ID Not Found"));
		return modelMapper.map(category, CategoryDTO.class);
	}

	@Override
	public List<CategoryDTO> getCategories() {
		// TODO Auto-generated method stub
		List<Category> categories = repository.findAll();

		return categories.stream().map(category -> modelMapper.map(category, CategoryDTO.class))
				.collect(Collectors.toList());
	}

}
