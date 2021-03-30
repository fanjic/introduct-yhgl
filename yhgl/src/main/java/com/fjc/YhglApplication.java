package com.fjc;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.fjc.mapper")
public class YhglApplication {

    public static void main(String[] args) {
        SpringApplication.run(YhglApplication.class, args);
    }

}
