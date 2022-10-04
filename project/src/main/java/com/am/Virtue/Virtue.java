package com.am.Virtue;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@SpringBootApplication
@EnableScheduling
@RestController
public class Virtue {
    @GetMapping("/Status")
    public String getStatus() {
        return "Application is running...!!!";
    }

    @Scheduled(fixedDelay = 500000)
    public void print() {
        System.out.println("Current Time : " + new Date());
    }

    public static void main(String[] args) {
        SpringApplication.run(Virtue.class, args);
    }

}
