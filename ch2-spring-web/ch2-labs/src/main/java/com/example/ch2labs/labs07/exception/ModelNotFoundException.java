package com.example.ch2labs.labs07.exception;

public class ModelNotFoundException extends RuntimeException {
    public ModelNotFoundException(Long id) {
        super("Could not find model with id " + id);
    }
}
