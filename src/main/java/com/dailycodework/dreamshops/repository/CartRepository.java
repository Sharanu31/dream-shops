package com.dailycodework.dreamshops.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dailycodework.dreamshops.entity.Cart;

public interface CartRepository extends JpaRepository<Cart, Long> {
	// Cart findByUserId(Long userId);

}
