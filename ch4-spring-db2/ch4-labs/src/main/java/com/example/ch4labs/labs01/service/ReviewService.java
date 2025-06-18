package com.example.ch4labs.labs01.service;

import com.example.ch4labs.labs01.domain.Review;
import com.example.ch4labs.labs01.dto.ReviewCreateRequest;
import com.example.ch4labs.labs01.dto.ReviewResponse;
import com.example.ch4labs.labs01.dto.ReviewUpdateRequest;
import com.example.ch4labs.labs01.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
@Transactional
public class ReviewService {

    // mapper -> JPA repository
    private final ReviewRepository postRepository;

    public ReviewResponse createReview(ReviewCreateRequest request) {
        Review review = request.toDomain();
        Review saved = postRepository.save(review);
        return ReviewResponse.from(saved);
    }

    @Transactional(readOnly = true)
    public List<ReviewResponse> getAllReviews() {
        return postRepository.findAll().stream()
                .map(reivew -> ReviewResponse.from(reivew))
                .toList();
    }

    @Transactional(readOnly = true)
    public ReviewResponse getReviewById(Long id) {
        return postRepository.findById(id)
                .map(ReviewResponse::from)
                // map(post -> PostResponse.from(post))
                .orElseThrow(() -> new NoSuchElementException("리뷰가 존재하지 않습니다."));
    }
//@Transactional
    public ReviewResponse updateReview(Long id, ReviewUpdateRequest request) {
        Review review = postRepository.findById(id)
                        .orElseThrow(() -> new NoSuchElementException("리뷰가 존재하지 않습니다."));

        // 트랜젝션이 끝날 때
        // 변경이 일어나면 dirty checking -> SQL

        review.setId(id);
        review.setTitle(request.getTitle());
        review.setContent(request.getContent());
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

    public void deletePost(Long id) {
        postRepository.deleteById(id);
    }



}