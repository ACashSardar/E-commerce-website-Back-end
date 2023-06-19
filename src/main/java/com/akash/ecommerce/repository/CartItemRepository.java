package com.akash.ecommerce.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.akash.ecommerce.entity.CartItem;
import com.akash.ecommerce.entity.User;

public interface CartItemRepository extends JpaRepository<CartItem, Integer> {
	List<CartItem> findCartItemByUser(User user);
}
