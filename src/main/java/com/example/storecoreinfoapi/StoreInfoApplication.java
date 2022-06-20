package com.example.storecoreinfoapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class StoreInfoApplication {

    public static void main(String[] args) {
        SpringApplication.run(StoreInfoApplication.class, args);
    }

}
