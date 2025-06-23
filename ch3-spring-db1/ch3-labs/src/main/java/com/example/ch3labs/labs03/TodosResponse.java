package com.example.ch3labs.todos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TodosResponse {
    private Long id;
    private String title;
    private boolean completed;

    public static TodosResponse from(Todos todos) {
        return new TodosResponse(todos.getId(), todos.getTitle(), todos.isCompleted());
    }
}
