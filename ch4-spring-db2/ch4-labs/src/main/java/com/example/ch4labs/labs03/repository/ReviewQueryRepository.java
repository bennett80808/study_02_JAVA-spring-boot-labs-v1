package com.example.ch4labs.labs03.repository;

import com.example.ch4labs.labs03.dto.ReviewResponse;
import com.example.ch4labs.labs03.dto.ReviewSearchRequest;
import org.springframework.data.domain.Page;

public interface ReviewQueryRepository {
    Page<ReviewResponse> search(ReviewSearchRequest request);
}
