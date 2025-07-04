package com.example.ch4labs.labs01.dto;

import com.example.ch4labs.labs01.domain.Review;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReviewUpdateRequest {
    private String title;
    private String content;
    private String author;
    private String bookTitle;
    private String bookAuthor;
    private int rating;

//    public Review toDomain() {
//        Review review = new Review();
//        review.setTitle(this.title);
//        review.setContent(this.content);
//        review.setAuthor(this.author);
//        review.setBookTitle(this.bookTitle);
//        review.setBookAuthor(this.bookAuthor);
//        review.setRating(this.rating);
//
//        return review;
//    }
}