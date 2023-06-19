package com.akash.ecommerce.serviceImpl;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.akash.ecommerce.entity.Review;
import com.akash.ecommerce.repository.ReviewRepository;
import com.akash.ecommerce.service.ReviewService;

@Service
public class ReviewServiceImpl implements ReviewService {

	@Autowired
	ReviewRepository reviewRepository;
	
	@Override
	public Review addReview(Review review) {
		review.setTime(LocalDateTime.now());
		return reviewRepository.save(review);
	}

	@Override
	public Review updateReview(int reviewId, Review review) {
		Review review2=reviewRepository.findById(reviewId).get();
		review2.setComment(review.getComment());
		review2.setRating(review.getRating());
		review2.setTime(LocalDateTime.now());
		review2.setProduct(review.getProduct());
		review2.setUser(review.getUser());
		return reviewRepository.save(review2);
	}

	@Override
	public boolean deleteReview(int reviewId) {
		reviewRepository.deleteById(reviewId);
		return true;
	}

	@Override
	public List<Review> getAllReviews() {
		
		return reviewRepository.findAll();
	}

	@Override
	public Review getReviewById(int reviewId) {
		
		return reviewRepository.findById(reviewId).get();
	}

}
