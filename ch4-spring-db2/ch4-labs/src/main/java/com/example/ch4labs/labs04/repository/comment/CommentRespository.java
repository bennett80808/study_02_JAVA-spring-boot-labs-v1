package com.example.ch4labs.labs04.repository.comment;

import com.example.ch4labs.labs04.domain.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CommentRespository extends JpaRepository<Comment, Long> {
    Page<Comment> findByReviewId(Long reviewId, Pageable pageable);
}
