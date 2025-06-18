package com.example.ch4labs.labs01.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity // DB의 테이블과 매핑 (posts)
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
}
