package com.dailycodework.dreamshops.service;

import java.math.BigDecimal;

import org.springframework.stereotype.Service;

import com.dailycodework.dreamshops.entity.Cart;
import com.dailycodework.dreamshops.repository.CartRepository;
import com.dailycodework.dreamshops.serviceImp.ICartService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CartService implements ICartService {

	private final CartRepository cartRepository;

	@Override
	public Cart getCart(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void clearCart(Long id) {
		// TODO Auto-generated method stub

	}

	@Override
	public BigDecimal getTotalPrice(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Long initializeNewCart() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Cart getCartByUserId(Long userId) {
		// TODO Auto-generated method stub
		return null;
	}

}
