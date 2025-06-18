package com.example.ch4labs.labs01.repository;

import com.example.ch4labs.labs01.domain.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {
    // CRUD
}
