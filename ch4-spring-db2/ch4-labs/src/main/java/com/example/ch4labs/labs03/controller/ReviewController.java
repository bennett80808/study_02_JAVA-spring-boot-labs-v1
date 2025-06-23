package com.example.ch4labs.labs03.controller;

import com.example.ch4labs.labs03.dto.*;
import com.example.ch4labs.labs03.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
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

    @GetMapping
    public ResponseEntity<List<ReviewResponse>> getAll() {
        return ResponseEntity.ok(service.getAllReviews());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReviewResponse> getById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getReviewById(id));
    }

    @GetMapping("/reviews")
    public ResponseEntity<ReviewPageResponse> searchReviews(@ModelAttribute ReviewSearchRequest request) {
        return ResponseEntity.ok(service.getAllSearchedReviews(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ReviewResponse> update(@PathVariable Long id, @RequestBody ReviewUpdateRequest request) {
        return ResponseEntity.ok(service.updateReview(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.deletereview(id);
        return ResponseEntity.noContent().build();
    }



}
