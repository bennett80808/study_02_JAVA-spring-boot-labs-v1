package com.example.ch4labs.labs03.repository;


import com.example.ch4labs.labs03.domain.QReview;
import com.example.ch4labs.labs03.domain.Review;
import com.example.ch4labs.labs03.dto.ReviewResponse;
import com.example.ch4labs.labs03.dto.ReviewSearchRequest;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.util.StringUtils;

import java.util.List;

@RequiredArgsConstructor
public class ReviewQueryRepositoryImpl implements ReviewQueryRepository {

    private final JPAQueryFactory queryFactory;

    @Override
    public Page<ReviewResponse> search(ReviewSearchRequest request){

        QReview review = QReview.review;

        BooleanBuilder builder = new BooleanBuilder();

        if(StringUtils.hasText(request.getBookTitleContains()) && request.getMinRating() != null && request.getMaxRating() != null){
            builder.and(
                    review.bookTitle.contains(request.getBookTitleContains())
                            .and(review.rating.between(request.getMinRating(), request.getMaxRating()))
            );
        }
//
        if(StringUtils.hasText(request.getTitleContains())){
            builder.and(
                    review.title.contains(request.getTitleContains())
            );
        }

        if(StringUtils.hasText(request.getAuthor()) && StringUtils.hasText(request.getBookAuthor())){
            builder.and(
                    review.author.eq(request.getAuthor())
                            .and(review.bookAuthor.eq(request.getBookAuthor()))
            );
        }

        if(StringUtils.hasText(request.getContentContains()) && request.getRating() != null){
            builder.and(
                    review.content.contains(request.getContentContains())
                            .and(review.rating.eq(request.getRating()))
            );
        }

        if(StringUtils.hasText(request.getBookTitle()) && StringUtils.hasText(request.getAuthor())){
            builder.and(
                    review.bookTitle.eq(request.getBookTitle())
                            .and(review.bookAuthor.eq(request.getAuthor()))
            );
        }

        if(StringUtils.hasText(request.getTitleContains()) && StringUtils.hasText(request.getContentContains()){
            builder.and(
                    review.title.contains(request.getTitleContains())
                            .or(review.content.contains(request.getContentContains()))
            );
        }

        if(StringUtils.hasText(request.getBookAuthor())){
            builder.and(
                    review.bookAuthor.eq(request.getBookAuthor())
            );
        }
        //
        if(StringUtils.hasText(request.getAuthor()) && request.getMinRating()!= null && request.getMaxRating()!= null&& StringUtils.hasText(request.getTitleContains())){
            builder.and(
                    review.author.eq(request.getAuthor())
                            .and(review.rating.between(request.getMinRating(), request.getMaxRating()))
            );
        }
    }
}
