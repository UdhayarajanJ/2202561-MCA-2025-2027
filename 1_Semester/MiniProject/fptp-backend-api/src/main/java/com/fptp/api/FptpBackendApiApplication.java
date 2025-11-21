package com.fptp.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(exclude = {org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration.class})
public class FptpBackendApiApplication {
    public static void main(String[] args) {
        SpringApplication.run(FptpBackendApiApplication.class, args);
    }
}
