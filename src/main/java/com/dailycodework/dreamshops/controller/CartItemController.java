package com.dailycodework.dreamshops.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dailycodework.dreamshops.exceptions.ResourceNotFoundException;
import com.dailycodework.dreamshops.response.ApiResponses;
import com.dailycodework.dreamshops.serviceImp.ICartItemService;
import com.dailycodework.dreamshops.serviceImp.ICartService;
import static org.springframework.http.HttpStatus.*;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("${api.prefix}/cartItems")
public class CartItemController {

	private final ICartItemService cartItemService;
	private final ICartService cartService;

	@PostMapping("/item/add")
	public ResponseEntity<ApiResponses> addItemToCart(
			@RequestParam(required = false) Long cartId,
			@RequestParam Long productId, @RequestParam Integer quantity) {
		try {
			if (cartId == null) {
				cartId = cartService.initializeNewCart();
			} 
			cartItemService.addItemToCart(cartId, productId, quantity);
			return ResponseEntity.ok(new ApiResponses("Add Item Success", null));
		} catch (ResourceNotFoundException e) {
			return ResponseEntity.status(NOT_FOUND).
					body(new ApiResponses(e.getMessage(), null));
		}
	}

	@DeleteMapping("/cart/{cartId}/item/{itemId}/remove")
	public ResponseEntity<ApiResponses> removeItemFromCart(
			@PathVariable Long cartId, @PathVariable Long itemId) {
		try {
			cartItemService.removeItemFromCart(cartId, itemId);
			return ResponseEntity.ok(new ApiResponses("Remove Item Success", null));
		} catch (ResourceNotFoundException e) {
			return ResponseEntity.status(NOT_FOUND).body(new ApiResponses(e.getMessage(), null));
		}
	}

	@PutMapping("/cart/{cartId}/item/{itemId}/update")
	public ResponseEntity<ApiResponses> updateItemQuantity(@PathVariable Long cartId, @PathVariable Long itemId,
			@RequestParam Integer quantity) {
		try {
			cartItemService.updateItemQuantity(cartId, itemId, quantity);
			return ResponseEntity.ok(new ApiResponses("Update Item Success", null));
		} catch (ResourceNotFoundException e) {
			return ResponseEntity.status(NOT_FOUND).body(new ApiResponses(e.getMessage(), null));
		}

	}
}
