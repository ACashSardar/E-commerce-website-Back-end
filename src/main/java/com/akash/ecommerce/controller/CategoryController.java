package com.akash.ecommerce.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.akash.ecommerce.entity.Category;
import com.akash.ecommerce.service.CategoryService;


@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/v1/categories")
public class CategoryController {
	
	@Autowired
	CategoryService categoryService;
	
	
	@GetMapping
	public List<Category> getAllCategorys(){
		return categoryService.getAllCategories();
	}
	
	@PostMapping
	public Category createCategory(@RequestBody Category category){
		return categoryService.addCategory(category);
	}
	
	@GetMapping("/{categoryId}")
	public Category getCategoryById(@PathVariable("categoryId") int categoryId){
		return categoryService.getCategoryById(categoryId);
	}
	
	@DeleteMapping("/{categoryId}")
	public void deleteCategory(@PathVariable("categoryId") int categoryId){
		categoryService.deleteCategory(categoryId);
	}

	@PutMapping("/{categoryId}")
	public Category updateCategory(@PathVariable("categoryId") int categoryId,@RequestBody Category category) {
		return categoryService.updateCategory(categoryId, category);
	}

}
