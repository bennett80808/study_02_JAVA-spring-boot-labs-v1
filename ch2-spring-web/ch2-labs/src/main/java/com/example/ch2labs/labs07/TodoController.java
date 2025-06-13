package com.example.ch2labs.labs07;

import com.example.ch2labs.labs07.DTO.RequestCreateDTO;
import com.example.ch2labs.labs07.DTO.RequestUpdateDTO;
import com.example.ch2labs.labs07.DTO.ResponseDTO;
import com.example.ch2labs.labs07.exception.InvalidTodoRequestException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/todos")
@RequiredArgsConstructor
public class TodoController {
    private final TodoService todoService;

    @PostMapping()
    public ResponseEntity<ResponseDTO> createTodo(@RequestBody RequestCreateDTO requestCreateDTO) {
        if(requestCreateDTO.getTitle() == null || requestCreateDTO.getTitle().trim().isEmpty()) {
            throw new InvalidTodoRequestException("Title cannot be empty");
        }
        if (requestCreateDTO.getCompleted() == null){
            throw new InvalidTodoRequestException("Completed status cannot be empty");
        }

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(todoService.createModel(requestCreateDTO));
    }
    @GetMapping()
    public ResponseEntity<List<ResponseDTO>> getAllTodos() {
        return ResponseEntity.ok(todoService.getAllTodos());
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseDTO> updateTodo(@PathVariable long id, @RequestBody RequestUpdateDTO requestUpdateDTO) {
        return ResponseEntity.ok(todoService.updateModel(id, requestUpdateDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTodo(@PathVariable long id) {
        todoService.deleteModel(id);
        return ResponseEntity.noContent().build();
    }
}
