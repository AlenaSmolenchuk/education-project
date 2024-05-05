package ru.mts.educationproject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableScheduling
@EnableJpaRepositories
@EnableTransactionManagement
public class Main {
    public static void main(String[] args) {

        SpringApplication.run(Main.class, args);

    }
}