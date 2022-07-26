package com.example.gulimall.product.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@ConfigurationProperties(prefix = "thread.pool")
public class ThreadPoolProperties {
    private int coreSize;
    private int maxSize;
    private int keepAliveTime;
}
