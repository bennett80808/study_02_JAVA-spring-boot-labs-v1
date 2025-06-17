package com.example.ch3labs.labs01.exceptions;

public class ExternalTodoNotFoundException extends RuntimeException {
    public ExternalTodoNotFoundException(String message) {
        super(message);
    }
}
