package com.null01;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("com.null01")
@SpringBootApplication

public class MybatistestApplication {

    public static void main(String[] args) {
        SpringApplication.run(MybatistestApplication.class, args);
    }

}
