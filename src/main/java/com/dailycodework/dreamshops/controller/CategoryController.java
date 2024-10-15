package com.dailycodework.dreamshops.controller;

import static org.springframework.http.HttpStatus.*;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dailycodework.dreamshops.entity.Category;
import com.dailycodework.dreamshops.exceptions.AlreadyExistsException;
import com.dailycodework.dreamshops.exceptions.ResourceNotFoundException;
import com.dailycodework.dreamshops.response.ApiResponses;
import com.dailycodework.dreamshops.serviceImp.ICategoryServices;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("${api.prefix}/category")
public class CategoryController {
	private final ICategoryServices categoryService;

	@GetMapping("/all")
	public ResponseEntity<ApiResponses> getAllCategories() {
		try {
			List<Category> categories = categoryService.getAllCategories();
			return ResponseEntity.ok(new ApiResponses("Found!", categories));
		} catch (Exception e) {
			return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(new ApiResponses("Error:", INTERNAL_SERVER_ERROR));
		}
	}

	@PostMapping("/add")
	public ResponseEntity<ApiResponses> addCategory(@RequestBody Category name) {
		try {
			Category theCategory = categoryService.addCategory(name);
			return ResponseEntity.ok(new ApiResponses("Success", theCategory));
		} catch (AlreadyExistsException e) {
			return ResponseEntity.status(CONFLICT).body(new ApiResponses(e.getMessage(), null));
		}
	}

	@GetMapping("/category/{id}/category")
	public ResponseEntity<ApiResponses> getCategoryById(@PathVariable Long id) {
		try {
			Category theCategory = categoryService.getCategoryById(id);
			return ResponseEntity.ok(new ApiResponses("Found", theCategory));
		} catch (ResourceNotFoundException e) {
			return ResponseEntity.status(NOT_FOUND).body(new ApiResponses(e.getMessage(), null));
		}
	}

	@GetMapping("/category/{name}/category")
	public ResponseEntity<ApiResponses> getCategoryByName(@PathVariable String name) {
		try {
			Category theCategory = categoryService.getCategoryByName(name);
			return ResponseEntity.ok(new ApiResponses("Found", theCategory));
		} catch (ResourceNotFoundException e) {
			return ResponseEntity.status(NOT_FOUND).body(new ApiResponses(e.getMessage(), null));
		}
	}

	@DeleteMapping("/category/{id}/delete")
	public ResponseEntity<ApiResponses> deleteCategory(@PathVariable Long id) {
		try {
			categoryService.deleteCategoryById(id);
			return ResponseEntity.ok(new ApiResponses("Deleted", null));
		} catch (ResourceNotFoundException e) {
			return ResponseEntity.status(NOT_FOUND).body(new ApiResponses(e.getMessage(), null));
		}
	}

	@PutMapping("/category/{id}/update")
	public ResponseEntity<ApiResponses> updateCategory(@PathVariable Long id, @RequestBody Category category) {
		try {
			Category updatedCategory = categoryService.updateCategory(category, id);
			return ResponseEntity.ok(new ApiResponses("Update success!", updatedCategory));
		} catch (ResourceNotFoundException e) {
			return ResponseEntity.status(NOT_FOUND).body(new ApiResponses(e.getMessage(), null));
		}
	}

}
