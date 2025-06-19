package com.example.ch4labs.labs02.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReviewSearchRequest {
    private String author;
    private String bookTitle;
    private Integer rating;
    private Integer minRating = 0;
    private Integer maxRating = 5;
    private int page = 0;
    private int size = 10;
}
