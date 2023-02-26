package com.spotify;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@Slf4j
@SpringBootApplication
@ComponentScan(basePackages = "com.spotify")
@EnableAutoConfiguration

public class AutomationApplication {
    public static void main(String[] args) {
        SpringApplication.run(AutomationApplication.class, args);
        log.info("Application is started with Spring");
    }
}
