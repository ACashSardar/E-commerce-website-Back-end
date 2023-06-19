package com.akash.ecommerce.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.akash.ecommerce.entity.Order;
import com.akash.ecommerce.entity.User;

public interface OrderRepository extends JpaRepository<Order, Integer>{
	List<Order> findOrderByUser(User user);
	List<Order> findOrderByDeliveryPerson(User user);
}
