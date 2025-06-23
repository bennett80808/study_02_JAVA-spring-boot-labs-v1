package com.example.ch4labs.labs04.dto.review;

import com.example.ch4labs.labs04.domain.Review;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReviewResponse {
    private Long id;             // 저장된 리뷰의 식별자
    private String title;
    private String content;
    private String author;
    private String bookTitle;
    private String bookAuthor;
    private int rating;

    public static ReviewResponse from(Review review) {
        return new ReviewResponse(
                review.getId(),
                review.getTitle(),
                review.getContent(),
                review.getAuthor(),
                review.getBookTitle(),
                review.getBookAuthor(),
                review.getRating()
        );
    }
}
