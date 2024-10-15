package com.dailycodework.dreamshops.controller;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.HttpStatus.NOT_FOUND;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dailycodework.dreamshops.entity.Product;
import com.dailycodework.dreamshops.request.AddProductRequest;
import com.dailycodework.dreamshops.request.ProductUpdateRequest;
import com.dailycodework.dreamshops.response.ApiResponses;
import com.dailycodework.dreamshops.serviceImp.IProductService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("${api.prefix}/product")
public class ProductController {
	private final IProductService productService;

	@GetMapping("/all")
	public ResponseEntity<ApiResponses> getAllProducts() {
		List<Product> products = productService.getAllProducts();
		return ResponseEntity.ok(new ApiResponses("success", products));
	}

	@GetMapping("/product/{productId}/product")
	public ResponseEntity<ApiResponses> getProductById(@PathVariable Long productId) {
		try {
			Product product = productService.getProductById(productId);
			return ResponseEntity.ok(new ApiResponses("success", product));
		} catch (Exception e) {
			return ResponseEntity.status(NOT_FOUND).body(new ApiResponses(e.getMessage(), null));
		}
	}

	@PostMapping("/add")
	public ResponseEntity<ApiResponses> addProduct(@RequestBody AddProductRequest product) {
		try {
			Product theProduct = productService.addProduct(product);
			return ResponseEntity.ok(new ApiResponses("Add product success!", theProduct));
		} catch (Exception e) {
			return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(new ApiResponses(e.getMessage(), null));
		}
	}

	@PutMapping("/product/{productId}/update")
	public ResponseEntity<ApiResponses> updateProduct(@RequestBody ProductUpdateRequest request,
			@PathVariable Long productId) {
		try {
			Product theProduct = productService.updateProduct(request, productId);
			return ResponseEntity.ok(new ApiResponses("Update product success!", theProduct));
		} catch (Exception e) {
			return ResponseEntity.status(NOT_FOUND).body(new ApiResponses(e.getMessage(), null));
		}
	}

	@DeleteMapping("/product/{productId}/delete")
	public ResponseEntity<ApiResponses> deleteProduct(@PathVariable Long productId) {
		try {
			productService.deleteProductById(productId);
			return ResponseEntity.ok(new ApiResponses("Delete product success!", productId));
		} catch (Exception e) {
			return ResponseEntity.status(NOT_FOUND).body(new ApiResponses(e.getMessage(), null));
		}
	}

	@GetMapping("/products/by/brand-and-name")
	public ResponseEntity<ApiResponses> getProductByBrandAndName(@RequestParam String brandName,
			@RequestParam String productName) {
		try {
			List<Product> products = productService.getProductsByBrandAndName(brandName, productName);
			if (products.isEmpty()) {
				return ResponseEntity.status(NOT_FOUND).body(new ApiResponses("No products found ", null));
			}
			// List<ProductDto> convertedProducts =
			// productService.getConvertedProducts(products);
			return ResponseEntity.ok(new ApiResponses("success", products));
		} catch (Exception e) {
			return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(new ApiResponses(e.getMessage(), null));
		}
	}

	@GetMapping("/products/by/category-and-brand")
	public ResponseEntity<ApiResponses> getProductByCategoryAndBrand(@RequestParam String category,
			@RequestParam String brand) {
		try {
			List<Product> products = productService.getProductsByCategoryAndBrand(category, brand);
			if (products.isEmpty()) {
				return ResponseEntity.status(NOT_FOUND).body(new ApiResponses("No products found ", null));
			}
			// List<ProductDto> convertedProducts =
			// productService.getConvertedProducts(products);
			return ResponseEntity.ok(new ApiResponses("success", products));
		} catch (Exception e) {
			return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(new ApiResponses("error", e.getMessage()));
		}
	}

	@GetMapping("/product/{category}/all/products")
	public ResponseEntity<ApiResponses> findProductByCategory(@PathVariable String category) {
		try {
			List<Product> products = productService.getProductsByCategory(category);
			if (products.isEmpty()) {
				return ResponseEntity.status(NOT_FOUND).body(new ApiResponses("No products found ", null));
			}
			// List<ProductDto> convertedProducts =
			// productService.getConvertedProducts(products);
			return ResponseEntity.ok(new ApiResponses("success", products));
		} catch (Exception e) {
			return ResponseEntity.ok(new ApiResponses(e.getMessage(), null));
		}
	}

	@GetMapping("/product/count/by-brand/and-name")
	public ResponseEntity<ApiResponses> countProductsByBrandAndName(@RequestParam String brand,
			@RequestParam String name) {
		try {
			var productCount = productService.countProductsByBrandAndName(brand, name);
			return ResponseEntity.ok(new ApiResponses("Product count!", productCount));
		} catch (Exception e) {
			return ResponseEntity.ok(new ApiResponses(e.getMessage(), null));
		}
	}

	@GetMapping("/product/by-brand")
	public ResponseEntity<ApiResponses> findProductByBrand(@RequestParam String brand) {
		try {
			List<Product> products = productService.getProductsByBrand(brand);
			if (products.isEmpty()) {
				return ResponseEntity.status(NOT_FOUND).body(new ApiResponses("No products found ", null));
			}
			// List<ProductDto> convertedProducts =
			// productService.getConvertedProducts(products);
			return ResponseEntity.ok(new ApiResponses("success", products));
		} catch (Exception e) {
			return ResponseEntity.ok(new ApiResponses(e.getMessage(), null));
		}
	}

}
