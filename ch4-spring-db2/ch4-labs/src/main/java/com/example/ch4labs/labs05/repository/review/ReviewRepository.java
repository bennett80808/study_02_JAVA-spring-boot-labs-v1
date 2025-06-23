package com.example.ch4labs.labs05.repository.review;

import com.example.ch4labs.labs05.domain.Review;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ReviewRepository extends JpaRepository<com.example.ch4labs.labs05.domain.Review, Long>, ReviewRepositoryQueryDSL {
    // CRUD
//    Page<Review> findByBookTitleContaining(String bookTitle, Pageable pageable);
//
//    Page<Review> findByAuthorAndRating(String author, int rating, Pageable pageable);
//
//    @Query("SELECT r FROM Review r WHERE r.rating BETWEEN :minRating AND :maxRating")
//    Page<Review> searchByMinRatingAndMaxRating(@Param("minRating") int minRating, @Param("maxRating") int maxRating, Pageable pageable);
//
//    @Query("SELECT r FROM Review r WHERE r.bookTitle LIKE %:bookTitle% AND r.author LIKE :author AND r.rating = :rating")
//    Page<Review> searchByTitleContainingAndAuthorAndRating(@Param("bookTitle") String bookTitle, @Param("author") String author, @Param("rating") int rating, Pageable pageable);

    @Query("SELECT r FROM Review r LEFT JOIN FETCH r.comments WHERE r.id = :id")
    Optional<Review> findByIdWithComments(@Param("id") Long id);
}
