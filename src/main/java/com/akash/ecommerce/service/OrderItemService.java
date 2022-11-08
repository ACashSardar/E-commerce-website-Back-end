package com.akash.ecommerce.service;

import java.util.List;

import com.akash.ecommerce.entity.OrderItem;

public interface OrderItemService {
	OrderItem addOrderItem(OrderItem orderItem);
	boolean deleteOrderItem(int orderItemId);
	List<OrderItem> getAllOrderItems();
}
