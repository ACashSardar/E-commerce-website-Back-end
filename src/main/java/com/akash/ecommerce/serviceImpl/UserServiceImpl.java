package com.akash.ecommerce.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.akash.ecommerce.entity.User;
import com.akash.ecommerce.repository.UserRepository;
import com.akash.ecommerce.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserRepository userRepository;

	@Override
	public User addUser(User user) {
		user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
		if (user.getProfilePic().equals("")) {
			user.setProfilePic("default_profile_pic.jpg");
		}
		return userRepository.save(user);
	}

	@Override
	public User updateUser(int userId, User user) {
		User user2 = userRepository.findById(userId).get();
		user2.setEmail(user.getEmail());
		user2.setPassword(user.getPassword());
		user2.setName(user.getName());
		user2.setAddress(user.getAddress());
		user2.setPhoneNo(user.getPhoneNo());
		user2.setRole(user.getRole());
		if(user.getProfilePic()!=null) user2.setProfilePic(user.getProfilePic());
		return userRepository.save(user2);
	}

	@Override
	public boolean deleteUser(int userId) {

		userRepository.deleteById(userId);
		return true;
	}

	@Override
	public List<User> getAllUsers() {

		return userRepository.findAll();
	}

	@Override
	public User getUserById(int userId) {

		return userRepository.findById(userId).get();
	}

	@Override
	public User getUserByEmail(String email) {
		return userRepository.findOneByEmail(email);
	}

}
