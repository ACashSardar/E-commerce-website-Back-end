package com.akash.ecommerce.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.akash.ecommerce.entity.OrderItem;
import com.akash.ecommerce.repository.OrderItemRepository;
import com.akash.ecommerce.service.OrderItemService;

@Service
public class OrderItemServiceImpl implements OrderItemService {

	@Autowired
	OrderItemRepository orderItemRepository;
	
	@Override
	public OrderItem addOrderItem(OrderItem orderItem) {
		
		return orderItemRepository.save(orderItem);
	}

	@Override
	public boolean deleteOrderItem(int orderItemId) {
		
		orderItemRepository.deleteById(orderItemId);
		return true;
	}

	@Override
	public List<OrderItem> getAllOrderItems() {
		
		return orderItemRepository.findAll();
	}


}
