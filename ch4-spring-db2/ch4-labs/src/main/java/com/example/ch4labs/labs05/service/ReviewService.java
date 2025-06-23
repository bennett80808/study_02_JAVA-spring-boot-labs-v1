package com.example.ch4labs.labs05.service;

import com.example.ch4labs.labs05.domain.Review;
import com.example.ch4labs.labs05.dto.comment.CommentPageResponse;
import com.example.ch4labs.labs05.dto.comment.CommentSearchRequest;
import com.example.ch4labs.labs05.dto.review.*;
import com.example.ch4labs.labs05.repository.review.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
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
    private final CommentService commentService;

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
    public ReviewWithCommentsResponse getReviewById(Long id) {
        return reviewRepository.findByIdWithComments(id)
                .map(ReviewWithCommentsResponse::from)
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

    public void deleteReview(Long id) {
        reviewRepository.deleteById(id);
    }


    @Transactional(readOnly = true)
    public ReviewWithCommentResponsePaging getReviewwithPaginatedComments(Long reviewId, CommentSearchRequest commentRequest) {
        // 1. 리뷰 자체 정보만 조회
        Review review = reviewRepository.findById(reviewId)
                .orElseThrow(()-> new NoSuchElementException("리뷰가 존재하지 않습니다."));
        // 2. 댓글 서비스를 통해 계층형 페이징된 댓글 조회
        CommentPageResponse commentPageResponse = commentService.getCommentsByReview(reviewId, commentRequest);
        // 3. 게시글과 페이징된 댓글 정보를 합쳐서 응답 생성
        return ReviewWithCommentResponsePaging.builder()
                .review(ReviewResponse.from(review))
                .commentPage(commentPageResponse)
                .build();
    }

}