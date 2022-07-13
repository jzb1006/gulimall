package com.example.gulimall.product.config;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;

import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@Configurable
public class MyThreadConfig {
    @Bean
    public ThreadPoolExecutor threadPoolExecutor(ThreadPoolProperties threadPoolProperties) {
        return new ThreadPoolExecutor(
                threadPoolProperties.getCoreSize(),
                threadPoolProperties.getMaxSize(),
                threadPoolProperties.getKeepAliveTime(),
                TimeUnit.SECONDS,
                new LinkedBlockingQueue<>(1024),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.AbortPolicy()
        );
    }
}
