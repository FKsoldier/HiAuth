package com.lmq.hidemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan(basePackages = "com.lmq.*")
@SpringBootApplication (scanBasePackages="com.lmq.hidemo.controller")
public class HidemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(HidemoApplication.class, args);
    }

}
