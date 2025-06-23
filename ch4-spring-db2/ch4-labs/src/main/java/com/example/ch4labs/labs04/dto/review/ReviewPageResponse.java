package com.example.ch4labs.labs04.dto.review;

import com.example.ch4labs.labs04.domain.Review;
import com.example.ch4labs.labs04.dto.review.ReviewResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
public class ReviewPageResponse {

    private int page;
    private int size;
    private long totalElement;
    private int totalPages;
    private List<ReviewResponse> reviewResponses;

    public static ReviewPageResponse from(Page<Review> reviewPage, int page) {

        return new ReviewPageResponse(
                page,
                reviewPage.getSize(),
                reviewPage.getTotalElements(),
                reviewPage.getTotalPages(),
                reviewPage.getContent().stream().map(ReviewResponse::from).collect(Collectors.toList())
        );
    }
}
