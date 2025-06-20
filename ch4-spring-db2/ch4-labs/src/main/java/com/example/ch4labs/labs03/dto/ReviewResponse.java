package com.example.ch4labs.labs03.dto;

import com.example.ch4labs.labs03.domain.Review;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

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
    private LocalDateTime createdAt;

    public static ReviewResponse from(Review review) {
        return new ReviewResponse(
                review.getId(),
                review.getTitle(),
                review.getContent(),
                review.getAuthor(),
                review.getBookTitle(),
                review.getBookAuthor(),
                review.getRating(),
                review.getCreatedAt()
        );
    }
}
