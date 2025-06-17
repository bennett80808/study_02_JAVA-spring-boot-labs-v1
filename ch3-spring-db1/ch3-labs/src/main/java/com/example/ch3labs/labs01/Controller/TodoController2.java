package com.example.ch3labs.labs01.Controller;

import com.example.ch3labs.labs01.DTO.ErrorResponseDTO;
import com.example.ch3labs.labs01.DTO.SuccessfulResponseDTO;
import com.example.ch3labs.labs01.Service.TodoService2;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/external-todos")
@RequiredArgsConstructor
public class TodoController2 {

    private final TodoService2 todoService;

    @GetMapping({"", "/"})  // → /external-todos/ 요청
    public ResponseEntity<ErrorResponseDTO> handleMissingId() {
        throw new IllegalArgumentException("ID가 누락되었습니다. /external-todos/{id} 형식으로 요청해주세요.");
    }

    @GetMapping("/{id}")
    public ResponseEntity<SuccessfulResponseDTO> getTodoById(@PathVariable Long id) {
        if(id==null || id <=0){
            throw new IllegalArgumentException("id must be greater than 0 and not null");
        }
        SuccessfulResponseDTO result = todoService.getTodo(id);
        if(result == null){
            throw new IllegalArgumentException("id " + id + " not found");
        }
        return ResponseEntity.ok(result);
    }
}
//Controller는 요청을 받고, 유효한지 확인하는 책임
//
//Service는 비즈니스 로직 수행이 책임
//
//그래서 지금처럼 id <= 0, null 여부를 Controller에서 검사하면 아주 좋음