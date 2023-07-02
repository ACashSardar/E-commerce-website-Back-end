package com.akash.ecommerce.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.akash.ecommerce.configuration.AppConstants;
import com.akash.ecommerce.entity.CartItem;
import com.akash.ecommerce.entity.Product;
import com.akash.ecommerce.entity.User;
import com.akash.ecommerce.service.CartItemService;
import com.akash.ecommerce.service.ProductService;
import com.akash.ecommerce.service.UserService;

@RestController
@CrossOrigin(origins = AppConstants.client_base_url)
@RequestMapping("/api/v1/cartitems")
public class CartItemController {

	@Autowired
	CartItemService cartItemService;

	@Autowired
	ProductService productService;

	@Autowired
	UserService userService;

	@GetMapping("/user/{email}")
	public List<CartItem> getCartItemsByUser(@PathVariable("email") String email) {
		User user = userService.getUserByEmail(email);
		return cartItemService.getCartItemByUser(user);
	}

	@PostMapping("/product/{productId}/user/{email}")
	public CartItem createCartItem(@PathVariable("productId") int productId, @PathVariable("email") String email,
			@RequestBody CartItem cartItem) {
		Product product = productService.getProductById(productId);
		User user = userService.getUserByEmail(email);
		cartItem.setProduct(product);
		cartItem.setUser(user);
		return cartItemService.addCartItem(cartItem);
	}

	@GetMapping("/{cartItemId}")
	public CartItem getCartItemById(@PathVariable("cartItemId") int cartItemId) {
		return cartItemService.getCartItemById(cartItemId);
	}

	@DeleteMapping("/{cartItemId}")
	public void deleteCartItem(@PathVariable("cartItemId") int cartItemId) {
		cartItemService.deleteCartItem(cartItemId);
	}

	@PutMapping("/{cartItemId}")
	public CartItem updateCartItem(@PathVariable("cartItemId") int cartItemId, @RequestBody CartItem cartItem) {
		return cartItemService.updateCartItem(cartItemId, cartItem);
	}

}
