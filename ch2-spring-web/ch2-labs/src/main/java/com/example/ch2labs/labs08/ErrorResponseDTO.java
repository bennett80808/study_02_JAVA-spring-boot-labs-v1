package com.example.ch2labs.labs08;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Map;

@AllArgsConstructor
@Getter
public class ErrorResponseDTO {
    private Map<String, String> errors;

}
