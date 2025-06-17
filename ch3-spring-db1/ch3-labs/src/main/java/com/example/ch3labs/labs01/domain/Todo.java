package com.example.ch3labs.labs01.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Todo {
    private Long id;
    private Long userId;
    private String title;
    private Boolean completed;

}
