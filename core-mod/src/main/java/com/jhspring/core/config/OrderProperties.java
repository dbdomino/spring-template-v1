package com.jhspring.core.config;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.bind.ConstructorBinding;
import org.springframework.stereotype.Component;

@Getter
@RequiredArgsConstructor
@ConfigurationProperties(prefix = "order")
public class OrderProperties {
    private final int defaultLimit;

}