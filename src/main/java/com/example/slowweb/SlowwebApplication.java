package com.example.slowweb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@SpringBootApplication
public class SlowwebApplication {

    public static void main(String[] args) {
        SpringApplication.run(SlowwebApplication.class, args);
    }

}




