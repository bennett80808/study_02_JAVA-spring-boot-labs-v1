package com.example.ch4labs.labs04.repository.review;

import com.example.ch4labs.labs04.domain.Review;
import com.example.ch4labs.labs04.dto.review.ReviewSearchRequest;
import org.springframework.data.domain.Page;

public interface ReviewRepositoryQueryDSL {
    Page<Review> search(ReviewSearchRequest reviewSearchRequest);

}
