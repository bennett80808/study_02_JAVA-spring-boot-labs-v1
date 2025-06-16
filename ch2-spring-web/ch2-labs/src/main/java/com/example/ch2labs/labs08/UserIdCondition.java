package com.example.ch2labs.labs08;

import java.lang.annotation.Documented;

import jakarta.validation.Constraint;
import jakarta.validation.constraints.*;
import jakarta.validation.Payload;


import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = UserIdValidator.class)
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface UserIdCondition {
    String message() default "영문 소문자와 숫자로 구성, 5~12자 사이";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
