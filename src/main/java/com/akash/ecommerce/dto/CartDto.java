package com.akash.ecommerce.dto;

import java.util.ArrayList;
import java.util.List;

import com.akash.ecommerce.entity.CartItem;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CartDto {
	private String userEmail;
	private int totalAmount;
	private List<CartItem> cartItems=new ArrayList<>();
}
