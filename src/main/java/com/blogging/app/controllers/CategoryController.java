package com.blogging.app.controllers;

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

import com.blogging.app.payloads.CategoryDTO;
import com.blogging.app.services.CategoryService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/categories")
public class CategoryController {
	
	@Autowired
	CategoryService categoryService;
	
	@PostMapping("/")
	public ResponseEntity<CategoryDTO> createCategory(
			@Valid @RequestBody CategoryDTO categoryDTO) {
		CategoryDTO newCategory = categoryService.createCategory(categoryDTO);
		return new ResponseEntity<>(newCategory, HttpStatus.OK);
	}
	
	@PutMapping("/{categoryId}")
	public ResponseEntity<CategoryDTO> udpateCategory(
			@Valid @RequestBody CategoryDTO categoryDTO,
			@PathVariable int categoryId) {
		
		CategoryDTO updatedCategory = categoryService.udpateCategory(categoryDTO, categoryId);
		return new ResponseEntity<>(updatedCategory, HttpStatus.OK);
	}
	
	@DeleteMapping("/{categoryId}")
	public ResponseEntity<CategoryDTO> deleteCategory(@PathVariable int categoryId) {
		
		categoryService.deleteCategory(categoryId);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@GetMapping("/{categoryId}")
	public ResponseEntity<CategoryDTO> getCategory(@PathVariable int categoryId) {
		
		CategoryDTO categoryDTO = categoryService.getCategory(categoryId);
		return new ResponseEntity<>(categoryDTO, HttpStatus.OK);
	}
	
	@GetMapping("/")
	public ResponseEntity<List<CategoryDTO>> getCategories() {
		
		List<CategoryDTO> categories = categoryService.getCategories();
		return new ResponseEntity<>(categories, HttpStatus.OK);
	}
	
}
