package com.akash.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.akash.ecommerce.entity.User;

public interface UserRepository extends JpaRepository<User, Integer> {
	User findOneByEmail(String email);
}
