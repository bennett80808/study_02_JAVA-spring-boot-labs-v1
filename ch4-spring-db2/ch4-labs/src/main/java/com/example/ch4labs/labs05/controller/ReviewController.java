package com.example.ch4labs.labs05.controller;


import com.example.ch4labs.labs05.dto.comment.CommentSearchRequest;
import com.example.ch4labs.labs05.dto.review.*;
import com.example.ch4labs.labs05.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/review")
public class ReviewController {

    private final ReviewService service;

    @PostMapping
    public ResponseEntity<ReviewResponse> create(@RequestBody ReviewCreateRequest request) {
        return ResponseEntity.ok(service.createReview(request));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReviewWithCommentsResponse> getById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getReviewById(id));
    }

    @GetMapping
    public ResponseEntity<List<ReviewResponse>> getAll() {
        return ResponseEntity.ok(service.getAllReviews());
    }



    @GetMapping("/reviews")
    public ResponseEntity<ReviewPageResponse> searchReviews(@ModelAttribute ReviewSearchRequest request) {
        return ResponseEntity.ok(service.getAllSearchedReviews(request));
    }

    @GetMapping("/{id}/paging")
    public ResponseEntity<ReviewWithCommentResponsePaging> getReviewWithPaginatedComments(
            @PathVariable Long id,
            @ModelAttribute CommentSearchRequest commentRequest){
        return ResponseEntity.ok(service.getReviewwithPaginatedComments(id, commentRequest));
    }


    @PutMapping("/{id}")
    public ResponseEntity<ReviewResponse> update(@PathVariable Long id, @RequestBody ReviewUpdateRequest request) {
        return ResponseEntity.ok(service.updateReview(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.deleteReview(id);
        return ResponseEntity.noContent().build();
    }



}
