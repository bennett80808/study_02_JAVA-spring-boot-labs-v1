package com.example.ch2labs.labs08;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@ValidTimeRange
public class RequestDTO {
    @NotBlank
    @UserIdCondition
    private String userId;
    @NotNull
    private LocalDateTime startTime;
    @NotNull
    private LocalDateTime endTime;
}
