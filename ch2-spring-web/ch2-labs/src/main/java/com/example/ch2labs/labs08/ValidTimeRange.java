package com.example.ch2labs.labs08;

import java.lang.annotation.Documented;

import jakarta.validation.Constraint;
import jakarta.validation.constraints.*;
import jakarta.validation.Payload;


import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = TimeValidator.class)
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidTimeRange {
    String message() default "시작 시간은 종료 시간보다 이전이어야 합니다.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
