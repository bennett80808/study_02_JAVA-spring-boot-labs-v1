package com.example.ch3labs.labs01.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class SuccessfulResponseDTO {
    private Long userId;
    private Long id;
    private String title;
    private Boolean completed;
}
