package com.example.ch4labs.labs03.service;

import com.example.ch4labs.labs03.domain.Review;
import com.example.ch4labs.labs03.dto.*;
import com.example.ch4labs.labs03.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class ReviewService {
    private final ReviewRepository reviewRepository;

    public ReviewResponse createReview(ReviewCreateRequest reviewCreateRequest) {
        Review save = reviewRepository.save(reviewCreateRequest.toDomain());

        return ReviewResponse.from(save);
    }


    public ReviewPageResponse findAll(ReviewSearchRequest reviewSearchRequest) {
        Page<Review> search = reviewRepository.search(reviewSearchRequest);

        return ReviewPageResponse.from(search, reviewSearchRequest.getPage());
    }

    public ReviewResponse update(long id, ReviewUpdateRequest reviewUpdateRequest) {
        Review review = new Review();
        review.setId(id);
        review.setTitle(reviewUpdateRequest.getTitle());
        review.setContent(reviewUpdateRequest.getContent());
        review.setAuthor(reviewUpdateRequest.getAuthor());
        review.setBookTitle(reviewUpdateRequest.getBookTitle());
        review.setBookAuthor(reviewUpdateRequest.getBookAuthor());
        review.setRating(reviewUpdateRequest.getRating());

        Review updated = reviewRepository.save(review);

        return ReviewResponse.from(updated);
    }

    public void delete(long id) {
        Review reviewById = getReviewById(id);
        reviewRepository.delete(reviewById);
    }

    public Review getReviewById(long id) {
        return reviewRepository.findById(id).orElse(null);
    }
}
