package com.example.ch4labs.labs03.controller;


import com.example.ch4labs.labs03.dto.ReviewCreateRequest;
import com.example.ch4labs.labs03.dto.ReviewSearchRequest;
import com.example.ch4labs.labs03.dto.ReviewUpdateRequest;
import com.example.ch4labs.labs03.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/reviews")
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;

    @PostMapping
    public ResponseEntity<?> addReview(@RequestBody ReviewCreateRequest reviewCreateRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(reviewService.createReview(reviewCreateRequest));
    }

    @GetMapping
    public ResponseEntity<?> getReviews(ReviewSearchRequest reviewSearchRequest) {
        return ResponseEntity.status(HttpStatus.OK).body(reviewService.findAll(reviewSearchRequest));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateReview(@PathVariable long id, @RequestBody ReviewUpdateRequest reviewUpdateRequest) {
        return ResponseEntity.status(HttpStatus.OK).body(reviewService.update(id, reviewUpdateRequest));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteReview(@PathVariable long id) {
        reviewService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
