package com.dailycodework.dreamshops.controller;

import java.math.BigDecimal;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dailycodework.dreamshops.entity.Cart;
import com.dailycodework.dreamshops.exceptions.ResourceNotFoundException;
import com.dailycodework.dreamshops.response.ApiResponses;
import com.dailycodework.dreamshops.serviceImp.ICartService;

import lombok.RequiredArgsConstructor;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@RequiredArgsConstructor
@RestController
@RequestMapping("${api.prefix}/carts")
public class CartController {

	private final ICartService cartService;

	@GetMapping("/{cartId}/my-cart")
	public ResponseEntity<ApiResponses> getCart(@PathVariable Long cartId) {
		try {
			Cart cart = cartService.getCart(cartId);
			return ResponseEntity.ok(new ApiResponses("Success", cart));
		} catch (ResourceNotFoundException e) {
			return ResponseEntity.status(NOT_FOUND).body(new ApiResponses(e.getMessage(), null));
		}
	}

	@DeleteMapping("/{cartId}/clear")
	public ResponseEntity<ApiResponses> clearCart(@PathVariable Long cartId) {
		try {
			cartService.clearCart(cartId);
			return ResponseEntity.ok(new ApiResponses("Clear Cart Success!", null));
		} catch (ResourceNotFoundException e) {
			return ResponseEntity.status(NOT_FOUND).body(new ApiResponses(e.getMessage(), null));
		}
	}

	@GetMapping("/{cartId}/cart/total-price")
	public ResponseEntity<ApiResponses> getTotalAmount(@PathVariable Long cartId) {
		try {
			BigDecimal totalPrice = cartService.getTotalPrice(cartId);
			return ResponseEntity.ok(new ApiResponses("Total Price", totalPrice));
		} catch (ResourceNotFoundException e) {
			return ResponseEntity.status(NOT_FOUND).body(new ApiResponses(e.getMessage(), null));
		}
	}
}
