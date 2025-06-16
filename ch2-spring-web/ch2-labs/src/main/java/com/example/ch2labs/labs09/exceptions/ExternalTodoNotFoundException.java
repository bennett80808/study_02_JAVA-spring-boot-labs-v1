package com.example.ch2labs.labs09.exceptions;

public class ExternalTodoNotFoundException extends RuntimeException {
    public ExternalTodoNotFoundException(String message) {
        super(message);
    }
}
