package com.example.ch4labs;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = "com.example.ch4labs.labs05")
@EnableJpaRepositories(basePackages = "com.example.ch4labs.labs05")
@EntityScan(basePackages = "com.example.ch4labs.labs05")
public class Ch4LabsApplication {

    public static void main(String[] args) {
        SpringApplication.run(Ch4LabsApplication.class, args);
    }

}
