package com.example.ch4labs.labs04.controller;

import com.example.ch4labs.labs04.dto.comment.CommentCreateRequest;
import com.example.ch4labs.labs04.dto.comment.CommentPageResponse;
import com.example.ch4labs.labs04.dto.comment.CommentResponse;
import com.example.ch4labs.labs04.dto.comment.CommentUpdateRequest;
import com.example.ch4labs.labs04.service.CommentService;
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
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size){

        Pageable pageable = PageRequest.of(page, size, Sort.by("createdAt").ascending());

        CommentPageResponse response = commentService.getCommentsByReview(reviewId, pageable);
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
