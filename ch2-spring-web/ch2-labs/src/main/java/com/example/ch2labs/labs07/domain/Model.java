package com.example.ch2labs.labs07.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Model {
    private Long id;
    private String title;
    private boolean completed;
}
