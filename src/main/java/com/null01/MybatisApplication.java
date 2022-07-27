package com.null01;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * The main file of application.
 * There is no any logic, only entrance to program.
 * Contains link to mapper.
 *
 * @Authors Vladimir, Evgeniy
 * @Version 1.0 (First_rabochaya)
 * @Since 09.06.2022
 */

@MapperScan("com.null01.mappers")
@SpringBootApplication
public class MybatisApplication {

    public static void main(String[] args) {
        SpringApplication.run(MybatisApplication.class, args);
    }

}