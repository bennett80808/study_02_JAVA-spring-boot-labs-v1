package com.example.ch4labs.labs02.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class ReviewPageResponse {

    private int page;
    private int size;
    private long totalCount;
    private int totalPages;
    private List<ReviewResponse> posts;

    public static ReviewPageResponse from(List<ReviewResponse> reviews, ReviewSearchRequest search, Long count) {
        int totalPages = (int) Math.ceil((double) count / search.getSize());
        return new ReviewPageResponse(
                search.getPage(),
                search.getSize(),
                count,
                totalPages,
                reviews
        );
    }
}
