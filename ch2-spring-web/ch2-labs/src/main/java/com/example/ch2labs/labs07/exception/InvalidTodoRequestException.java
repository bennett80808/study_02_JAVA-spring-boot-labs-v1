package com.example.ch2labs.labs07.exception;

public class InvalidTodoRequestException extends RuntimeException {
    public InvalidTodoRequestException(String message) {
        super(message);
    }
}
