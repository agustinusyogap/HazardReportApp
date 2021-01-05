package com.ksm.hazardreportapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class HazardreportappApplication {

    public static void main(String[] args) {
        SpringApplication.run(HazardreportappApplication.class, args);
    }

    @Bean //Inversion of Control
    public RestTemplate getRestTemplate() {
        return new RestTemplate();
    }

}
