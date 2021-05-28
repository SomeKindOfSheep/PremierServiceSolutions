package com.pss.premierservicesolutions;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@SpringBootApplication
@EnableScheduling
@ComponentScan("com.pss")
public class PremierServiceSolutionsApplication {

    public static void main(String[] args) {
        SpringApplication.run(PremierServiceSolutionsApplication.class, args);
    }

}
