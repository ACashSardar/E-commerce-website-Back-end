package com.akash.ecommerce.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.akash.ecommerce.entity.Product;
import com.akash.ecommerce.entity.Review;
import com.akash.ecommerce.entity.User;
import com.akash.ecommerce.service.ProductService;
import com.akash.ecommerce.service.ReviewService;
import com.akash.ecommerce.service.UserService;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/v1/reviews")
public class ReviewController {
	@Autowired
	ReviewService reviewService;
	
	@Autowired
	UserService userService;
	
	@Autowired
	ProductService productService;
	
	@PostMapping("/product/{productId}/user/{email}")
	public Review createReview(@RequestBody Review review,
			@PathVariable("productId") Integer productId,
			@PathVariable("email") String email) {
		User user=userService.getUserByEmail(email);
		Product product=productService.getProductById(productId);
		review.setUser(user);
		review.setProduct(product);
		return reviewService.addReview(review);
	}
	
	@DeleteMapping("/{reviewId}")
	public void deleteReview(@PathVariable("reviewId") Integer reviewId) {
		reviewService.deleteReview(reviewId);
	}
	
	@PutMapping("/{reviewId}")
	public Review updateReview(@PathVariable("reviewId") Integer reviewId, @RequestBody Review review) {
		return reviewService.updateReview(reviewId, review);
	}
}
