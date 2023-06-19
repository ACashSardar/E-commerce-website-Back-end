package com.akash.ecommerce.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.akash.ecommerce.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Integer> {
	List<Product> findProductByCatId(int catId);
}
