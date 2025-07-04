package com.example.ch2labs.labs08;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Model {
    private Long ModelId;
    private String userId;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
}
