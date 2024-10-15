package com.dailycodework.dreamshops.service;

import org.springframework.stereotype.Service;

import com.dailycodework.dreamshops.entity.CartItem;
import com.dailycodework.dreamshops.repository.CartItemRepository;
import com.dailycodework.dreamshops.serviceImp.ICartItemService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CartItemService implements ICartItemService {

	 private final CartItemRepository cartItemRepository;
	 
	@Override
	public void addItemToCart(Long cartId, Long productId, int quantity) {
		// TODO Auto-generated method stub

	}

	@Override
	public void removeItemFromCart(Long cartId, Long productId) {
		// TODO Auto-generated method stub

	}

	@Override
	public void updateItemQuantity(Long cartId, Long productId, int quantity) {
		// TODO Auto-generated method stub

	}

	@Override
	public CartItem getCartItem(Long cartId, Long productId) {
		// TODO Auto-generated method stub
		return null;
	}

}
