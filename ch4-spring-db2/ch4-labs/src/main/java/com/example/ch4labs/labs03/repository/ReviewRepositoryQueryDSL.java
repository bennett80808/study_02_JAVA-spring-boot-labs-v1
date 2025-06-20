package com.example.ch4labs.labs03.repository;

import com.example.ch4labs.labs03.domain.Review;
import com.example.ch4labs.labs03.dto.ReviewSearchRequest;
import org.springframework.data.domain.Page;

public interface ReviewRepositoryQueryDSL {
    Page<Review> search(ReviewSearchRequest reviewSearchRequest);

}
