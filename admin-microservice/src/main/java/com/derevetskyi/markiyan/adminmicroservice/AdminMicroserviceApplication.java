package com.derevetskyi.markiyan.adminmicroservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class AdminMicroserviceApplication {

    public static void main(String[] args) {
        SpringApplication.run(AdminMicroserviceApplication.class, args);
    }

}
