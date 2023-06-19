package com.akash.ecommerce.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.akash.ecommerce.entity.User;
import com.akash.ecommerce.service.UserService;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/v1/users")
public class UserController {
	
	@Autowired
	UserService userService;
	
	@GetMapping
	public List<User> getAllUsers(){
		return userService.getAllUsers();
	}
	
	@PostMapping
	public User createUser(@RequestBody User user){
		return userService.addUser(user);
	}
	
	@GetMapping("/{userId}")
	public User getUserById(@PathVariable("userId") int userId){
		return userService.getUserById(userId);
	}
	
	@GetMapping("/user/{email}")
	public User getUserByEmail(@PathVariable("email") String email){
		User user=userService.getUserByEmail(email);
		return user;
	}
	
	@DeleteMapping("/{userId}")
	public void deleteUser(@PathVariable("userId") int userId){
		userService.deleteUser(userId);
	}

	@PutMapping("/{userId}")
	public User updateUser(@PathVariable("userId") int userId,@RequestBody User user) {
		return userService.updateUser(userId, user);
	}
	
	
	
	@GetMapping("/currUser")
	public User getCurrUser() {

		String username="";
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		User user=null;
		System.out.println(principal);
		if (principal instanceof UserDetails) {
		   username = ((UserDetails)principal).getUsername();
		   user=userService.getUserByEmail(username);
		   System.out.println(user.getName());
		   return user;
		} 
		return null;
	}
}
