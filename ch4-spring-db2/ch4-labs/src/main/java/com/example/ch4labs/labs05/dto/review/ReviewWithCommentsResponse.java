package com.example.ch4labs.labs05.dto.review;

import com.example.ch4labs.labs05.domain.Review;
import com.example.ch4labs.labs05.dto.comment.CommentResponse;
import com.example.ch4labs.labs05.dto.review.ReviewResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ReviewWithCommentsResponse {

    private ReviewResponse review;
    private List<CommentResponse> comments;

    public static ReviewWithCommentsResponse from(Review review){
        return ReviewWithCommentsResponse.builder()
                .review(ReviewResponse.from(review))
                .comments(
                        review.getComments().stream()
                                .map(CommentResponse::from)
                                .toList()
                )
                .build();
    }
}
