package com.dailycodework.dreamshops.serviceImp;

import java.util.List;

import com.dailycodework.dreamshops.entity.Category;

public interface ICategoryServices {

	    Category getCategoryById(Long id);
	    Category getCategoryByName(String name);
	    List<Category> getAllCategories();
	    Category addCategory(Category category);
	    Category updateCategory(Category category, Long id);
	    void deleteCategoryById(Long id);
}
