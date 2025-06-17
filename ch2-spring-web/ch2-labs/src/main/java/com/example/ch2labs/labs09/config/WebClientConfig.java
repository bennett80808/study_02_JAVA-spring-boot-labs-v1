package com.example.ch2labs.labs09.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {

    @Bean
    public WebClient jsonPlaceholderWebClient(){
        return WebClient.builder()
                .baseUrl("https://jsonplaceholder.typicode.com")
                .build();
    }
}
//@Configuration은 내부적으로 @Component를 포함하고 있어서
//스프링이 자동으로 이 클래스를 스캔해서 컨테이너에 등록합니다.
//
//이 클래스 안에서 @Bean 메서드를 사용하면:
//
//메서드 리턴값이 Spring 컨테이너에 빈으로 등록됩니다.
//
//이 빈들은 어디서든 @Autowired나 생성자 주입으로 사용할 수 있습니다.
//
