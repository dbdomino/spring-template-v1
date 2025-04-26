package com.jhspring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication(scanBasePackages = {
        "com.jhspring.data",
        "com.jhspring.core",
        "com.jhspring.common",
        "com.jhspring.user"
})
@ConfigurationPropertiesScan
public class UserAppMain {
    public static void main(String[] args) {
        SpringApplication.run(UserAppMain.class, args);
    }

}