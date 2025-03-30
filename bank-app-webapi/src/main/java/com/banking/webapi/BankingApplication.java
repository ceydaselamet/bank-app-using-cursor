package com.banking.webapi;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan(basePackages = "com.banking.*")
@EntityScan("com.banking.entities")
@EnableJpaRepositories("com.banking.repositories.abstracts")
@OpenAPIDefinition(
    info = @Info(
        title = "Banking Application API",
        version = "1.0",
        description = "REST API for Banking Application"
    )
)
public class BankingApplication {
    public static void main(String[] args) {
        SpringApplication.run(BankingApplication.class, args);
    }
} 