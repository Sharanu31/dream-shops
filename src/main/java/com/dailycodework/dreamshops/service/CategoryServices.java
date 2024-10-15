package com.dailycodework.dreamshops.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dailycodework.dreamshops.entity.Category;
import com.dailycodework.dreamshops.exceptions.AlreadyExistsException;
import com.dailycodework.dreamshops.exceptions.ResourceNotFoundException;
import com.dailycodework.dreamshops.repository.CategoryRepository;
import com.dailycodework.dreamshops.serviceImp.ICategoryServices;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CategoryServices implements ICategoryServices {

	@Autowired
	private final CategoryRepository categoryRepository;

	@Override
	public Category getCategoryById(Long id) {
		return categoryRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Category not found!"));
	}

	@Override
	public Category getCategoryByName(String name) {
		return categoryRepository.findByName(name);
	}

	@Override
	public List<Category> getAllCategories() {
		return categoryRepository.findAll();
	}

	@Override
	public Category addCategory(Category category) {
		return Optional.of(category).filter(c -> !categoryRepository.existsByName(c.getName()))
				.map(categoryRepository::save)
				.orElseThrow(() -> 
				new AlreadyExistsException(category.getName() + " already exists"));

	}

	@Override
	public Category updateCategory(Category category, Long id) {
		return Optional.ofNullable(getCategoryById(id)).map(oldCategory -> {
			oldCategory.setName(category.getName());
			return categoryRepository.save(oldCategory);
		}).orElseThrow(() -> new ResourceNotFoundException("Category not found!"));
	}

	@Override
	public void deleteCategoryById(Long id) {
		categoryRepository.findById(id).ifPresentOrElse(categoryRepository::delete, () -> {
			throw new ResourceNotFoundException("Category not found!");
		});
	}

}
