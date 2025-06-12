package com.captainyun7.ch2codeyourself._03_rest_controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/rest/v2")
public class RestController02 {
    // ResponseEntity = 스프링의 HTTP 응답 객체
    // HTTP : 헤더 + 바디 + 상태코드

    @GetMapping("/hello")
    public ResponseEntity<String> hello() {
        return ResponseEntity.ok("Hello World");
    }

    @GetMapping("/posts/{postId}")
    public ResponseEntity<Post> getPosts(@PathVariable Long postId) {
        Post post = new Post("샘플 게시글", "샘플 내용입니다...");
        post.setId(1L);
        return ResponseEntity.ok(post);
    }

    @GetMapping("/posts")
    public ResponseEntity<List<Post>> posts() {
        List<Post> posts = new ArrayList<Post>();
        posts.add(new Post("첫번째 게시글", "내용1"));
        posts.add(new Post("두번째 게시글", "내용2"));
        posts.add(new Post("세번째 게시글", "내용3"));
        return ResponseEntity.ok(posts);
    }

    @PostMapping("/posts")
    public ResponseEntity<Post> addPost(@RequestBody Post post) {
        post.setId(1L);
        return ResponseEntity.ok(post);
    }

    // HTTP 상태 코드
    // 2xx : 성공
    // 3xx : 리다이렉트, 진행중
    // 4xx : 에러 (클라이언트 실수)
    // 5xx : 에러 (서버 실수)

    @PostMapping("/201")
    public ResponseEntity<String> get201() {
        return ResponseEntity.status(HttpStatus.CREATED).body("201");
    }

    @DeleteMapping("/204")
    public ResponseEntity<String> delete204() {
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("204");
    }

    @GetMapping("/401")
    public ResponseEntity<String> get401() {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("401"); // 로그인이 안되었을때
    }
    
    @GetMapping("/403")
    public ResponseEntity<String> get403() {
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body("403"); // 권한이 없을 때
    }

    @GetMapping("500")
    public ResponseEntity<String> get500() {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("500");
    }

    @GetMapping("/plain-text")
    public ResponseEntity<String> plainText() {
        return ResponseEntity.ok().contentType(MediaType.TEXT_PLAIN)
                .body("hello");
    }

    @PostMapping("/location")
    public ResponseEntity<String> location() {
        // 리다이렉트 + location
        URI locaton = URI.create("/rest/v2/posts");
        return ResponseEntity.status(HttpStatus.FOUND).location(locaton).build();
    }
}
