package com.example.ch4labs.labs03.dto;

import com.example.ch4labs.labs03.dto.ReviewResponse;
import com.example.ch4labs.labs03.dto.ReviewSearchRequest;
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
    private List<com.example.ch4labs.labs03.dto.ReviewResponse> posts;

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
