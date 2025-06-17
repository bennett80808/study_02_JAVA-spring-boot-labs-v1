package com.example.ch3labs.labs01.Service;

import com.example.ch3labs.labs01.DTO.SuccessfulResponseDTO;
import com.example.ch3labs.labs01.domain.Todo;
import com.example.ch3labs.labs01.client.TodoApiClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TodoService2 {
    private final TodoApiClient todoApiClient;

    public SuccessfulResponseDTO getTodo(Long id) {
        Todo todo = todoApiClient.fetchTodo(id);

        if (todo == null || todo.getId() == null) {
            return null;
        }
        return toSuccessfulResponse(todo);
    }
    public SuccessfulResponseDTO toSuccessfulResponse(Todo todo){
        return new SuccessfulResponseDTO(todo.getUserId(), todo.getId(),todo.getTitle(),todo.getCompleted());
    }
}