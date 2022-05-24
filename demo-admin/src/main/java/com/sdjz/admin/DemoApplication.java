package com.sdjz.admin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.sdjz.*"})
public class DemoApplication {
    public static void main(String[] args) {
//        ConfigurableApplicationContext applicationContext = SpringApplication.run(DemoApplication.class);
        SpringApplication.run(DemoApplication.class);
    }
}
