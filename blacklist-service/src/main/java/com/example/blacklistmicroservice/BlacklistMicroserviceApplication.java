package com.example.blacklistmicroservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class BlacklistMicroserviceApplication {

    public static void main(String[] args) {
        SpringApplication.run(BlacklistMicroserviceApplication.class, args);
    }

}
