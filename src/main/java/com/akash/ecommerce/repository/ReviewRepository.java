package com.akash.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.akash.ecommerce.entity.Review;

public interface ReviewRepository extends JpaRepository<Review, Integer> {

}
