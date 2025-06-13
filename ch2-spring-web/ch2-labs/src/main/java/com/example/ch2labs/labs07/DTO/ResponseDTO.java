package com.example.ch2labs.labs07.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ResponseDTO {
    private Long id;
    private String title;
    private boolean completed;
}
