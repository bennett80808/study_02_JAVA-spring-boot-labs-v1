package com.example.ch4labs.labs05.repository.review;

import com.example.ch4labs.labs05.domain.Review;
import com.example.ch4labs.labs05.dto.review.ReviewSearchRequest;
import org.springframework.data.domain.Page;

public interface ReviewRepositoryQueryDSL {
    Page<Review> search(ReviewSearchRequest reviewSearchRequest);

}
