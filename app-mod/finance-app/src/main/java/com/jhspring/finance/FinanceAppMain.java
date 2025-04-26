package com.jhspring.finance;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication(scanBasePackages = {
        "com.jhspring.data",
        "com.jhspring.core",
        "com.jhspring.common",
        "com.jhspring.finance"
})
@ConfigurationPropertiesScan
public class FinanceAppMain {
    public static void main(String[] args) {
        SpringApplication.run(FinanceAppMain.class, args);
    }
}