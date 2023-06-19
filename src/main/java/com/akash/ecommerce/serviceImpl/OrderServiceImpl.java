package com.akash.ecommerce.serviceImpl;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.akash.ecommerce.entity.Order;
import com.akash.ecommerce.entity.User;
import com.akash.ecommerce.repository.OrderRepository;
import com.akash.ecommerce.service.OrderService;

@Service
public class OrderServiceImpl implements OrderService {

	@Autowired
	OrderRepository orderRepository;
	
	@Override
	public Order addOrder(Order order) {
		order.setOrderDate(LocalDateTime.now());
		order.setTransaction("Pending");
		return orderRepository.save(order);
	}

	@Override
	public Order updateOrder(int orderId, Order order) {
		Order order2=orderRepository.findById(orderId).get();
		order2.setOrderDate(LocalDateTime.now());
		if(order.getShippingAddress()!=null)
			order2.setShippingAddress(order.getShippingAddress());
		if(order.getTransaction()!=null)
			order2.setTransaction(order.getTransaction());
		return orderRepository.save(order2);
	}

	@Override
	public boolean deleteOrder(int orderId) {
		orderRepository.deleteById(orderId);
		return true;
	}

	@Override
	public List<Order> getAllOrders() {
		
		return orderRepository.findAll();
	}

	@Override
	public Order getOrderById(int orderId) {
		
		return orderRepository.findById(orderId).get();
	}

	@Override
	public List<Order> getOrdersByUser(User user) {
		
		return orderRepository.findOrderByUser(user);
	}

	@Override
	public Order assignDelivery(int orderId, Order order) {
		Order order2=orderRepository.findById(orderId).get();
		order2.setDeliveryPerson(order.getDeliveryPerson());
		return orderRepository.save(order2);
	}
	
	@Override
	public Order updateDelivery(int orderId, Order order) {
		Order order2=orderRepository.findById(orderId).get();
		order2.setDeliveryStatus(order.getDeliveryStatus());
		order2.setDeliveryDate(order.getDeliveryDate());
		return orderRepository.save(order2);
	}

	@Override
	public List<Order> getOrdersDeliveryPerson(User user) {
		
		return orderRepository.findOrderByDeliveryPerson(user);
	}

}
