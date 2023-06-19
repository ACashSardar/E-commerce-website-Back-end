package com.akash.ecommerce.service;

import java.util.List;

import com.akash.ecommerce.entity.CartItem;
import com.akash.ecommerce.entity.User;

public interface CartItemService {
	CartItem addCartItem(CartItem cartItem);
	CartItem updateCartItem(int cartItemId, CartItem cartItem);
	boolean deleteCartItem(int cartItemId);
	List<CartItem> getAllCartItems();
	CartItem getCartItemById(int cartItemId);
	List<CartItem> getCartItemByUser(User user);
}
