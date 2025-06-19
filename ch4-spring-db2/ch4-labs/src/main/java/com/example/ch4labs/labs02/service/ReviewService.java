package com.example.ch4labs.labs02.service;

import com.example.ch4labs.labs02.domain.Review;
import com.example.ch4labs.labs02.dto.*;
import com.example.ch4labs.labs02.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.swing.*;
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

    @Transactional(readOnly = true)
    public ReviewPageResponse getAllReivews(ReviewSearchRequest search) {

        // author 가 포함되어 있으면 -> findByAuthor
        // keyword 가 포함되어 있으면 -> findByTitleContaining

        Pageable pageable = PageRequest.of(search.getPage(), search.getSize());
//        Pageable :
//        "몇 페이지", "한 페이지에 몇 개씩" 보여줄지 설정하는 인터페이스
//        내부적으로 offset, limit, sort 정보를 가짐

        // /posts?keyword=스프링&author=윤지수

        Page<Review> reviews = null;
//        Page<T>란?
//        Spring Data JPA나 Spring Data Mongo, QueryDSL 등에서
//        자동으로 페이징된 결과를 담는 객체

//        List<T>처럼 데이터를 담고 있지만,
//        추가로 전체 개수, 현재 페이지, 다음 페이지 있음 여부, 총 페이지 수 등 정보가 들어 있음
        if (search.getBookTitle() != null && search.getAuthor() != null
                && search.getRating() != null) {
            reviews = postRepository.searchByTitleContainingAndAuthorAndRating(search.getBookTitle(), search.getAuthor(), search.getRating(), pageable);
        } else if (search.getBookTitle() != null) {
            reviews = postRepository.findByBookTitleContaining(search.getBookTitle(), pageable);
        } else if (search.getAuthor() != null && search.getRating() != null) {
            reviews = postRepository.findByAuthorAndRating(search.getAuthor(), search.getRating(), pageable);
        } else if (search.getMinRating() != null && search.getMaxRating() != null) {//
            reviews = postRepository.searchByMinRatingAndMaxRating(search.getMinRating(), search.getMaxRating(), pageable);
        } else {
            reviews = postRepository.findAll(pageable);
        }

        Page<ReviewResponse> page = reviews.map(review -> ReviewResponse.from(review));

        return ReviewPageResponse.from(page.getContent(), search, page.getTotalElements());
    }

    public void deletePost(Long id) {
        postRepository.deleteById(id);
    }



}