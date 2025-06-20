package com.example.ch4labs.labs03.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReviewSearchRequest {
    private String author;
    private String bookTitle;
    private String bookTitleContains;
    private String bookAuthor;
    private String titleContains;
    private String contentContains;
    private Integer rating;
    private Integer minRating = 0;
    private Integer maxRating = 5;
    private String sort;
    private int page = 0;
    private int size = 10;

    public Pageable toPageable() {
        String[] parts = sort.split(",");
        Sort.Direction direction = Sort.Direction.fromString(parts[1]);
        return PageRequest.of(page, size, Sort.by(direction, parts[0]));
    }
}
