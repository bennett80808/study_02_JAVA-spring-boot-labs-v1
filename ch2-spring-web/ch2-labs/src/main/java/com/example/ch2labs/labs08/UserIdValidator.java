package com.example.ch2labs.labs08;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import jakarta.validation.ConstraintValidatorContext;

import java.time.LocalDateTime;

public class UserIdValidator implements ConstraintValidator<UserIdCondition, String> {
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context){
        if(value == null || value.isBlank()) return true;

        boolean lengthCheck = value.length() >= 5 && value.length() <= 12;
        boolean hasLowercase = value.matches(".*[a-z].*");   // 소문자 하나 이상 포함
        return lengthCheck && hasLowercase;
    }
}
