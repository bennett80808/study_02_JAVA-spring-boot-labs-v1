package com.example.ch2labs.labs07;

import com.example.ch2labs.labs07.domain.Model;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DataInit {
    private final TodoRepository todoRepository;

    @PostConstruct
    public void init(){
        todoRepository.save(new Model(null, "1번째 글", false));
        todoRepository.save(new Model(null, "2번째 글", false));
        todoRepository.save(new Model(null, "3번째 글", false));
    }
}
