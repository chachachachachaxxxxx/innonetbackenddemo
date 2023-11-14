package com.example.innonetbackenddemo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.example.innonetbackenddemo.mapper")
public class InnonetbackenddemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(InnonetbackenddemoApplication.class, args);
    }

}
