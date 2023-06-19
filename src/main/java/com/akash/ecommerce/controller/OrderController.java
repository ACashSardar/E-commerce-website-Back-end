package com.akash.ecommerce.controller;

import java.time.LocalDateTime;
import java.util.ArrayList;
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

import com.akash.ecommerce.dto.CartDto;
import com.akash.ecommerce.entity.OrderItem;
import com.akash.ecommerce.entity.CartItem;
import com.akash.ecommerce.entity.Order;
import com.akash.ecommerce.entity.User;
import com.akash.ecommerce.repository.OrderRepository;
import com.akash.ecommerce.service.OrderItemService;
import com.akash.ecommerce.service.OrderService;
import com.akash.ecommerce.service.ProductService;
import com.akash.ecommerce.service.UserService;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/v1/orders")
public class OrderController {
	@Autowired
	OrderService orderService;
	
	@Autowired
	ProductService productService;
	
	@Autowired
	UserService userService;
	
	@Autowired
	OrderItemService orderItemService;
	
	@Autowired
	OrderRepository orderRepository;
	

	@GetMapping
	public List<Order> getAllOrders(){
		return orderService.getAllOrders();
	}
	
	@GetMapping("/user/{email}")
	public List<Order> getOrdersByUser(@PathVariable("email") String email){
		User user=userService.getUserByEmail(email);
		return orderService.getOrdersByUser(user);
	}
	
	@GetMapping("/deliveryPerson/{email}")
	public List<Order> getOrdersDeliveryPerson(@PathVariable("email") String email){
		User user=userService.getUserByEmail(email);
		return orderService.getOrdersDeliveryPerson(user);
	}
	
	
	@PostMapping("/user/{email}")
	public Order createOrder(@PathVariable("email") String email,@RequestBody CartDto cart){
		User user=userService.getUserByEmail(email);
		Order order=new Order();
		order.setUser(user);
		orderRepository.save(order);
		
		List<OrderItem> orderItems=new ArrayList<>();
		for(CartItem elem:cart.getCartItems()) {
			OrderItem orderItem=new OrderItem();
			orderItem.setOrder(order);
			orderItem.setUser(user);
			orderItem.setQuantity(elem.getQuantity());
			orderItem.setPaymentStatus(elem.getPaymentStatus());
			orderItem.setProduct(elem.getProduct());
			orderItems.add(orderItem);
			orderItemService.addOrderItem(orderItem);
		}
		order.setOrderItems(orderItems);
		order.setTotalAmount(cart.getTotalAmount());
		order.setTransaction("Pending");
		order.setOrderDate(LocalDateTime.now());
		return orderService.addOrder(order);
	}
	
	@GetMapping("/{orderId}")
	public Order getOrderById(@PathVariable("orderId") int orderId){
		return orderService.getOrderById(orderId);
	}
	
	@DeleteMapping("/{orderId}")
	public void deleteOrder(@PathVariable("orderId") int orderId){
		orderService.deleteOrder(orderId);
	}

	@PutMapping("/{orderId}")
	public Order updateOrder(@PathVariable("orderId") int orderId,@RequestBody Order order) {
		return orderService.updateOrder(orderId, order);
	}
	
	@GetMapping("/assign/{orderId}/delivery/{deliveryPersonId}")
	public Order assignDelivery(@PathVariable("orderId") int orderId,@PathVariable("deliveryPersonId") int deliveryPersonId) {
		User deliveryPerson=userService.getUserById(deliveryPersonId);
		Order order=orderService.getOrderById(orderId);
		order.setDeliveryPerson(deliveryPerson);
		return orderService.assignDelivery(orderId, order);
	}
	
	@PutMapping("/update/{orderId}")
	public Order updateDelivery(@PathVariable("orderId") int orderId,@RequestBody Order order) {
		return orderService.updateDelivery(orderId, order);
	}

}
