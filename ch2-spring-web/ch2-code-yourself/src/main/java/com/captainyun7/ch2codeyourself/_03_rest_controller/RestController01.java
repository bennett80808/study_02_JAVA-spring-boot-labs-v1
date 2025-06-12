package com.captainyun7.ch2codeyourself._03_rest_controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/rest/v1") // 모든 HTTP 메서드를 받음
public class RestController01 {
    @GetMapping("/hello")
    public String hello() {
        return "hello"; // HTML -> 데이터(API)
    }

    @GetMapping("/posts/{postId}") // 게시글 typicode
    public Post post(@PathVariable int postId) {
        Post post = new Post("샘플 게시글", "샘플 내용입니다...");
        post.setId(1L);
        return post;
    }

    @GetMapping("/posts")
    public List<Post> posts() {
        List<Post> posts = new ArrayList<Post>();
        posts.add(new Post("첫번째 게시글", "내용1"));
        posts.add(new Post("두번째 게시글", "내용2"));
        posts.add(new Post("세번째 게시글", "내용3"));
        return posts;
    }

    // POST 메서드 : http body 안에 내용을 담아 요청
    // http body { title: ..., body: ...}
    @PostMapping("/posts")
    public Post addPost(@RequestBody Post post) {
        post.setId(1L);
        return post;
    }
}
