package com.example.ch4labs.labs05.controller;

import com.example.ch4labs.labs05.dto.comment.*;
import com.example.ch4labs.labs05.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/review/comments")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @PostMapping("/{reviewId}/comment")
    public ResponseEntity<CommentResponse> createComment(
            @PathVariable Long reviewId,
            @RequestBody CommentCreateRequest request){

        CommentResponse response = commentService.createComment(reviewId, request);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/{reviewId}/comments")
    public ResponseEntity<CommentPageResponse> getComments(
            @PathVariable Long reviewId,
            @ModelAttribute CommentSearchRequest request){

        CommentPageResponse response = commentService.getCommentsByReview(reviewId, request);
        ResponseEntity<CommentPageResponse> result = ResponseEntity.ok(response);
        return result;
    }

    @PutMapping("/{commentId}")
    public ResponseEntity<CommentResponse> updateComment(
            @PathVariable Long commentId,
            @RequestBody CommentUpdateRequest request){

        CommentResponse response = commentService.updateComment(commentId, request);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("{commentId}")
    public ResponseEntity<Void> deleteComment(@PathVariable Long commentId){
        commentService.deleteComment(commentId);
        return ResponseEntity.noContent().build();
    }
}
