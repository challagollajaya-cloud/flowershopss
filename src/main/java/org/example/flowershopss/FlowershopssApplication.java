package org.example.flowershopss;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class FlowershopssApplication {

    public static void main(String[] args) {
        SpringApplication.run(
                FlowershopssApplication.class, args);
    }
}