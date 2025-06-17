package com.example.ch2labs.labs09.client;

import com.example.ch2labs.labs09.domain.Todo;
import com.example.ch2labs.labs09.exceptions.ExternalApiServerException;
import com.example.ch2labs.labs09.exceptions.ExternalTodoNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
//@RequiredArgsConstructor 가 아래 생성자를 자동으로 만들어줌
// public TodoApiClient(WebClient jsonPlaceholderWebClient) {
//    this.jsonPlaceholderWebClient = jsonPlaceholderWebClient;
//}
public class TodoApiClient {
    private final WebClient jsonPlaceholderWebClient;
//    jsonPlaceholderWebClient는 Spring 컨테이너에 등록된 WebClient 빈입니다.
//    @RequiredArgsConstructor가 자동으로 생성한 생성자에 의해,//
//    Spring이 WebClient 빈을 찾아서 자동으로 주입해줍니다.
//      그럼 어떤 빈이 주입되는가?
//    이 부분에서 등록된 @Bean 메서드의 리턴값이 주입됩니다:
    public Todo fetchTodo(Long id) {
        return jsonPlaceholderWebClient.get()
                .uri("todos/{id}", id)
                .retrieve()
                .onStatus(status -> status.is4xxClientError(),
                        response -> Mono.error(new ExternalTodoNotFoundException("해당 Todo가 존재하지 않습니다."))
                )
                .onStatus(status -> status.is5xxServerError(),
                        response -> Mono.error(new ExternalApiServerException("외부 API서버 오류입니다.")))
                .bodyToMono(Todo.class)
                .block();
    }
}
