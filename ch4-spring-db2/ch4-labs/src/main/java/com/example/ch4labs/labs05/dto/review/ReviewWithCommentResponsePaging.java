package com.example.ch4labs.labs05.dto.review;

import com.example.ch4labs.labs05.domain.Review;
import com.example.ch4labs.labs05.dto.comment.CommentPageResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ReviewWithCommentResponsePaging {

    private ReviewResponse review;
    private CommentPageResponse commentPage;

    public static ReviewWithCommentResponsePaging from(Review review, CommentPageResponse commentPage) {
        return ReviewWithCommentResponsePaging.builder()
                .review(ReviewResponse.from(review))
                .commentPage(commentPage)
                .build();
    }
}
