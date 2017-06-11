package com.itjobsaggregatorproject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.SecurityAutoConfiguration;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication(exclude = {SecurityAutoConfiguration.class}) //disable password
public class ItJobAggregatorApplication {

    public static void main(String[] args) {
        SpringApplication.run(ItJobAggregatorApplication.class, args);
    }

}
