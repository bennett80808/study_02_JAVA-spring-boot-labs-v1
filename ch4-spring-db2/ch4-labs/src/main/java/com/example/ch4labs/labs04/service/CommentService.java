package com.example.ch4labs.labs04.service;

import com.example.ch4labs.labs04.domain.Comment;
import com.example.ch4labs.labs04.domain.Review;
import com.example.ch4labs.labs04.dto.comment.CommentCreateRequest;
import com.example.ch4labs.labs04.dto.comment.CommentPageResponse;
import com.example.ch4labs.labs04.dto.comment.CommentResponse;
import com.example.ch4labs.labs04.dto.comment.CommentUpdateRequest;
import com.example.ch4labs.labs04.repository.comment.CommentRespository;
import com.example.ch4labs.labs04.repository.review.ReviewRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@Transactional
@RequiredArgsConstructor
public class CommentService {

    private final CommentRespository commentRepository;
    private final ReviewRepository reviewRepository;

    public CommentResponse createComment(Long reviewId, CommentCreateRequest request) {
        Review review = reviewRepository.findById(reviewId)
                .orElseThrow( ()-> new EntityNotFoundException("리뷰를 찾을 수 없습니다."));

        Comment comment = Comment.builder()
                .content(request.getContent())
                .author(request.getAuthor())
                .createdAt(LocalDateTime.now())
                .review(review)
                .build();

        Comment savedComment = commentRepository.save(comment);
        return CommentResponse.from(savedComment);
    }

    @Transactional(readOnly = true)
    public CommentPageResponse getCommentsByReview(Long reviewId, Pageable pageable) {
        Page<CommentResponse> page = commentRepository
                .findByReviewId(reviewId, pageable)
                .map(CommentResponse::from);

        return CommentPageResponse.from(page);
    }

    public CommentResponse updateComment(Long commentId, CommentUpdateRequest request) {
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow( ()-> new EntityNotFoundException("댓글이 없습니다."));

        comment.setContent(request.getContent());

        return CommentResponse.from(comment);
    }

    public void deleteComment(Long commentId) {
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow( ()-> new EntityNotFoundException("댓글이 없습니다."));

        commentRepository.delete(comment);
    }
}
