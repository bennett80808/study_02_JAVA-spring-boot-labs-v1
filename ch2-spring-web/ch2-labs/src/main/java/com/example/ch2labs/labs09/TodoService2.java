package com.example.ch2labs.labs09;

import com.example.ch2labs.labs07.DTO.ResponseDTO;
import com.example.ch2labs.labs09.exceptions.ExternalApiServerException;
import com.example.ch2labs.labs09.exceptions.ExternalTodoNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

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