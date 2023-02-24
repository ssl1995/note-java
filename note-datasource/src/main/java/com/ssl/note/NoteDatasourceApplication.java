package com.ssl.note;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = {"com.ssl.note.mapper"})
public class NoteDatasourceApplication {

    public static void main(String[] args) {
        SpringApplication.run(NoteDatasourceApplication.class, args);
    }

}
