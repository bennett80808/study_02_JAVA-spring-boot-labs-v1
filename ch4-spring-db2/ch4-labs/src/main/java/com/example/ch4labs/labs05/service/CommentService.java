package com.example.ch4labs.labs05.service;

import com.example.ch4labs.labs05.domain.Comment;
import com.example.ch4labs.labs05.domain.Review;
import com.example.ch4labs.labs05.dto.comment.*;
import com.example.ch4labs.labs05.repository.comment.CommentRepository;
import com.example.ch4labs.labs05.repository.review.ReviewRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final ReviewRepository reviewRepository;

    public CommentResponse createComment(Long reviewId, CommentCreateRequest request) {
        Review review = reviewRepository.findById(reviewId)
                .orElseThrow( ()-> new EntityNotFoundException("리뷰를 찾을 수 없습니다."));
        Comment parent = null;


        if(request.getParentId() != null) {
            parent = commentRepository.findById(request.getParentId())
                    .orElseThrow(() -> new IllegalArgumentException("부모 댓글이 존재하지 않습니다."));
            if(reviewId != parent.getReview().getId()) {
                throw new IllegalArgumentException("입력한 리뷰 아이디의 값이 Comment id 값이 검색한 parent_id 값인 댓글의 리뷰아이디와 일치하지 않습니다.");
            }
        }

         Comment comment = Comment.builder()
                .content(request.getContent())
                .author(request.getAuthor())
                .createdAt(LocalDateTime.now())
                .review(review)
                 .parent(parent)
                .build();

        Comment savedComment = commentRepository.save(comment);
        return CommentResponse.from(savedComment);
    }

    @Transactional(readOnly = true)
    public CommentPageResponse getCommentsByReview(Long reviewId, CommentSearchRequest request) {
        Pageable pageable = PageRequest.of(
                request.getPage(),
                request.getSize(),
                Sort.by("createdAt").ascending()
        );
        if (request.isHierarchical()){
            return getHierarchicalCommentsByReview(reviewId, pageable);
        } else {
            return getFlatCommentsByReview(reviewId, pageable);
        }
    }

    public CommentResponse updateComment(Long commentId, CommentUpdateRequest request) {
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow( ()-> new EntityNotFoundException("댓글이 없습니다."));

        comment.setContent(request.getContent());

        return CommentResponse.from(comment);
    }

    private CommentPageResponse getFlatCommentsByReview(Long reviewId, Pageable pageable) {
        Page<CommentResponse> page = commentRepository
                .findByReviewId(reviewId, pageable)
                .map(CommentResponse::from);

        return CommentPageResponse.from(page);

    }

    private CommentPageResponse getHierarchicalCommentsByReview(Long reviewId, Pageable pageable) {
//        특정 리뷰(reviewId)에 달린 부모 댓글만 페이징해서 가져오고,
//                각 부모 댓글에 해당하는 자식 댓글들을 붙여서 계층 구조로 구성한 후,
//        페이징 정보와 함께 응답 객체(CommentPageResponse)로 반환하는 메서드

        // 1. 부모 댓글만 페이징 처리하여 가져옴
        Page<Comment> parentCommentPage = commentRepository.findParentCommentsByReviewId(reviewId, pageable);

        List<CommentResponse> hierarchicalComments = new ArrayList<>();

        // 2. 각 부모 댓글에 대해 자식 댓글을 모두 조회하여 계층 구조로 변환
        for (Comment parentComment : parentCommentPage.getContent()) {
            CommentResponse parentResponse = CommentResponse.from(parentComment);

            // 3. 자식 댓글 조회 및 설정
            List<Comment> childComments = commentRepository.findByParentIdOrderByCreatedAtAsc(parentComment.getId());
            List<CommentResponse> childResponses = childComments.stream()
                    .map(CommentResponse::from)
                    .collect(Collectors.toList());

            parentResponse.setReplies(childResponses);
            hierarchicalComments.add(parentResponse);
        }

        // 4. 페이징 정보와 함께 응답 생성
        return CommentPageResponse.builder()
                .page(parentCommentPage.getNumber())
                .size(parentCommentPage.getSize())
                .totalElements((parentCommentPage.getTotalElements()))
                .comments(hierarchicalComments)
                .build();

    }

    public void deleteComment(Long commentId) {
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow( ()-> new EntityNotFoundException("댓글이 없습니다."));

        commentRepository.delete(comment);
    }
}
