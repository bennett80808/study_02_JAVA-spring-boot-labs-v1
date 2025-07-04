package com.example.ch4labs.labs03.service;

import com.example.ch4labs.labs03.domain.Review;
import com.example.ch4labs.labs03.dto.*;
import com.example.ch4labs.labs03.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
@Transactional
public class ReviewService {

    // mapper -> JPA repository
    private final ReviewRepository reviewRepository;

    public ReviewResponse createReview(ReviewCreateRequest request) {
        Review review = request.toDomain();
        Review saved = reviewRepository.save(review);
        return ReviewResponse.from(saved);
    }

    @Transactional(readOnly = true)
    public List<ReviewResponse> getAllReviews() {
        return reviewRepository.findAll().stream()
                .map(reivew -> ReviewResponse.from(reivew))
                .toList();
    }

    @Transactional(readOnly = true)
    public ReviewResponse getReviewById(Long id) {
        return reviewRepository.findById(id)
                .map(ReviewResponse::from)
                // map(review -> reviewResponse.from(review))
                .orElseThrow(() -> new NoSuchElementException("리뷰가 존재하지 않습니다."));
    }
//@Transactional
    public ReviewResponse updateReview(Long id, ReviewUpdateRequest request) {
        Review review = reviewRepository.findById(id)
                        .orElseThrow(() -> new NoSuchElementException("리뷰가 존재하지 않습니다."));

        // 트랜젝션이 끝날 때
        // 변경이 일어나면 dirty checking -> SQL

        review.setId(id);
        review.setTitle(request.getTitle());
        review.setContent(request.getContent());
        review.setAuthor(request.getAuthor());
        review.setBookTitle(request.getBookTitle());
        review.setBookAuthor(request.getBookAuthor());
        review.setRating(request.getRating());
        // 메서드 끝나면서 트랜잭션 커밋
        // setter로 저장한게
        // JPA가 변경 감지 → UPDATE 쿼리 자동 실행
        //  = DB에 save 안해도 save 동작을 함.
        return ReviewResponse.from(review);
    }

    @Transactional(readOnly = true)
    public ReviewPageResponse getAllSearchedReviews(ReviewSearchRequest request) {

        Page<Review> searched = reviewRepository.search(request);

        return ReviewPageResponse.from(searched, request.getPage());
    }

    public void deletereview(Long id) {
        reviewRepository.deleteById(id);
    }



}