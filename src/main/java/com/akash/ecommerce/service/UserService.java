package com.akash.ecommerce.service;

import java.util.List;

import com.akash.ecommerce.entity.User;

public interface UserService {
	User addUser(User user);
	User updateUser(int userId, User user);
	boolean deleteUser(int userId);
	List<User> getAllUsers();
	User getUserById(int userId);
	User getUserByEmail(String email);
}
