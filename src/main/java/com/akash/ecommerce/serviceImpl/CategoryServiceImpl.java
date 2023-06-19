package com.akash.ecommerce.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.akash.ecommerce.entity.Category;
import com.akash.ecommerce.repository.CategoryRepository;
import com.akash.ecommerce.service.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	CategoryRepository categoryRepository;
	
	@Override
	public Category addCategory(Category category) {
		
		return categoryRepository.save(category);
	}

	@Override
	public Category updateCategory(int categoryId, Category category) {
		Category category2=categoryRepository.findById(categoryId).get();
		category2.setCategoryName(category.getCategoryName());
		category2.setCategoryDesc(category.getCategoryDesc());
		return categoryRepository.save(category2);
	}

	@Override
	public boolean deleteCategory(int categoryId) {
		categoryRepository.deleteById(categoryId);
		return true;
	}

	@Override
	public List<Category> getAllCategories() {

		return categoryRepository.findAll();
	}

	@Override
	public Category getCategoryById(int categoryId) {
		
		return categoryRepository.findById(categoryId).get();
	}

}
