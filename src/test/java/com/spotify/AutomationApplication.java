package com.spotify;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Slf4j
@EnableTransactionManagement
@EnableJpaRepositories
@SpringBootApplication()
@ComponentScan(basePackages = "com.spotify")
@EnableAutoConfiguration

public class AutomationApplication {
    private static final org.slf4j.Logger LOG = LoggerFactory.getLogger(AutomationApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(AutomationApplication.class, args);
        LOG.info("Application is started with Spring");
    }
}
