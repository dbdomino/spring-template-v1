package com.jhspring.front;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {
        "com.jhspring.core",
        "com.jhspring.common",
        "com.jhspring.front"
})
public class FrontAppMain {
    public static void main(String[] args) {
        SpringApplication.run(FrontAppMain.class, args);
    }
}