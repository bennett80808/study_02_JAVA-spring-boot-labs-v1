package com.example.ch4labs.labs05.repository.comment;

import com.example.ch4labs.labs05.domain.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    Page<Comment> findByReviewId(Long reviewId, Pageable pageable);

    @Query("SELECT c FROM Comment c WHERE c.review.id = :reviewId AND c.parent IS NULL")
    Page<Comment> findParentCommentsByReviewId(@Param("reviewId")Long reviewId, Pageable pageable);

    List<Comment> findByParentIdOrderByCreatedAtAsc(Long parentId);
}
