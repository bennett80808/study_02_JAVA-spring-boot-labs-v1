package com.example.ch4labs.labs03.repository;

import com.example.ch4labs.labs03.domain.Review;
import com.example.ch4labs.labs03.dto.ReviewResponse;
import com.example.ch4labs.labs03.dto.ReviewSearchRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public interface ReviewRepository extends JpaRepository<com.example.ch4labs.labs03.domain.Review, Long>, ReviewQueryRepository {
    // CRUD
    Page<com.example.ch4labs.labs03.domain.Review> findByBookTitleContaining(String bookTitle, Pageable pageable);

    Page<com.example.ch4labs.labs03.domain.Review> findByAuthorAndRating(String author, int rating, Pageable pageable);

    @Query("SELECT r FROM Review r WHERE r.rating BETWEEN :minRating AND :maxRating")
    Page<com.example.ch4labs.labs03.domain.Review> searchByMinRatingAndMaxRating(@Param("minRating") int minRating, @Param("maxRating") int maxRating, Pageable pageable);

    @Query("SELECT r FROM Review r WHERE r.bookTitle LIKE %:bookTitle% AND r.author LIKE :author AND r.rating = :rating")
    Page<com.example.ch4labs.labs03.domain.Review> searchByTitleContainingAndAuthorAndRating(@Param("bookTitle") String bookTitle, @Param("author") String author, @Param("rating") int rating, Pageable pageable);

    Page<ReviewResponse> search(ReviewSearchRequest request);
}
