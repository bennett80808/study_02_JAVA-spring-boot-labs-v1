package com.example.ch2labs.labs08;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.time.LocalDateTime;

public class TimeValidator implements ConstraintValidator<ValidTimeRange, RequestDTO> {
@Override
    public boolean isValid(RequestDTO request, ConstraintValidatorContext context){
    if(request == null) return false;
    LocalDateTime start = request.getStartTime();
    LocalDateTime end = request.getEndTime();
    // null 체크 먼저! NPE 방지
    if (start == null || end == null) {
        return true; // @NotNull이 이걸 처리하게 두자
    }
    boolean result = start.isBefore(end);
    if (!result) {
        context.disableDefaultConstraintViolation();  // 기본 메시지 제거
        context.buildConstraintViolationWithTemplate("시작 시간은 종료 시간보다 이전이어야 합니다.")
                .addPropertyNode("startTime") // startTime 필드에 메시지를 붙이도록 지정
                .addConstraintViolation();
    }

    return result;
}
}
