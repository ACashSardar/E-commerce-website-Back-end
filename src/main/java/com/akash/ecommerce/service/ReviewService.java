package com.akash.ecommerce.service;

import java.util.List;

import com.akash.ecommerce.entity.Review;

public interface ReviewService {
	Review addReview(Review review);
	Review updateReview(int reviewId, Review review);
	boolean deleteReview(int reviewId);
	List<Review> getAllReviews();
	Review getReviewById(int reviewId);
}
