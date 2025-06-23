package com.example.ch4labs.labs05.dto.comment;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CommentPageResponse {

    private int page;
    private int size;
    private long totalElements;
    private int totalPages;
    private List<CommentResponse> comments;

            public static CommentPageResponse from(Page<CommentResponse> pageData) {
        return CommentPageResponse.builder()
                .page(pageData.getNumber())
                .size(pageData.getSize())
                .totalElements(pageData.getTotalElements())
                .comments(pageData.getContent())
                .build();
            }
}
