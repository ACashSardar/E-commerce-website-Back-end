package com.akash.ecommerce.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.akash.ecommerce.configuration.AppConstants;
import com.akash.ecommerce.entity.User;
import com.akash.ecommerce.service.FileService;
import com.akash.ecommerce.service.UserService;

import jakarta.servlet.http.HttpServletResponse;

@RestController
@CrossOrigin(origins = AppConstants.client_base_url)
@RequestMapping("/api/v1/users")
public class UserController {

	@Autowired
	UserService userService;

	@Autowired
	FileService fileService;

	@Value("${project.image}")
	private String path;

	@GetMapping
	public List<User> getAllUsers() {
		return userService.getAllUsers();
	}

	@PostMapping
	public User createUser(@RequestBody User user) {
		return userService.addUser(user);
	}

	@GetMapping("/{userId}")
	public User getUserById(@PathVariable("userId") int userId) {
		return userService.getUserById(userId);
	}

	@GetMapping("/user/{email}")
	public User getUserByEmail(@PathVariable("email") String email) {
		User user = userService.getUserByEmail(email);
		return user;
	}

	@DeleteMapping("/{userId}")
	public void deleteUser(@PathVariable("userId") int userId) {
		userService.deleteUser(userId);
	}

	@PutMapping(value = "/{userId}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public User updateUser(@RequestPart("file") MultipartFile file, @PathVariable("userId") int userId,
			@RequestPart("document") User user) throws IOException {
		System.out.println(file.getOriginalFilename());
		if (file != null && !file.getOriginalFilename().equals("default_profile_pic.jpg")) {
			String uniqueName = fileService.uploadImage(path, file);
			user.setProfilePic(uniqueName);
		}
		return userService.updateUser(userId, user);
	}

	@GetMapping("/currUser")
	public User getCurrUser() {
		String username = "";
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		User user = null;
		System.out.println(principal);
		if (principal instanceof UserDetails) {
			username = ((UserDetails) principal).getUsername();
			user = userService.getUserByEmail(username);
			return user;
		}
		return null;
	}

	@GetMapping(value = "/image/{imageName}", produces = MediaType.IMAGE_JPEG_VALUE)
	public void serveImage(@PathVariable("imageName") String imageName, HttpServletResponse response)
			throws IOException {
		InputStream resource = fileService.getResource(path, imageName);
		response.setContentType(MediaType.IMAGE_JPEG_VALUE);
		StreamUtils.copy(resource, response.getOutputStream());
	}
}
