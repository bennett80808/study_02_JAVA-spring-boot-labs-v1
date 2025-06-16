package com.example.ch2labs.labs09.exceptions;

import com.example.ch2labs.labs09.ErrorResponseDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorResponseDTO> illegalArgumentException(IllegalArgumentException e) {
        return ResponseEntity
                .badRequest()
                .body(new ErrorResponseDTO((e.getMessage())));
    }
    @ExceptionHandler(ExternalTodoNotFoundException.class)
    public ResponseEntity<ErrorResponseDTO> handleNotFound(ExternalTodoNotFoundException e) {
        return ResponseEntity
                .status(404)
                .body(new ErrorResponseDTO(e.getMessage()));
    }

    @ExceptionHandler(ExternalApiServerException.class)
    public ResponseEntity<ErrorResponseDTO> handleExternalError(ExternalApiServerException e) {
        return ResponseEntity
                .status(502) // 또는 500도 가능, 외부 API 실패를 의미할 땐 502가 적절
                .body(new ErrorResponseDTO(e.getMessage()));
    }
}
