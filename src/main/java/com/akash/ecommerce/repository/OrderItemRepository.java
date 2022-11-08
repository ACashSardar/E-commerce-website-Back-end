package com.akash.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.akash.ecommerce.entity.OrderItem;

public interface OrderItemRepository extends JpaRepository<OrderItem, Integer> {

}
