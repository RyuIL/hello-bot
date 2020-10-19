package com.example.hellobot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
public class HellobotApplication {

    public static void main(String[] args) {
        SpringApplication.run(HellobotApplication.class, args);
    }

}
