package com.example.ch4labs.labs04.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity // DB의 테이블과 매핑 (reviews)
@Table(name = "review")
public class Review {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)를 사용하면
//    DB가 직접 기본키(id)를 생성하고,
//    JPA는 저장(insert) 이후에 그 값을 받아서 엔티티에 주입해줘.


    private Long id;
    private String title;
    private String content;
    private String author;
    private String bookTitle;
    private String bookAuthor;
    private int rating;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @OneToMany(mappedBy = "review", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Comment> comments = new ArrayList<>();


}
//Review (1) ↔ Comment (N): 1:N 양방향 관계 설정
//
//mappedBy = "review": Comment 엔티티에서 review라는 필드가 **외래키의 주인(Owner)**이라는 뜻
