package com.example.gulimall.order.config;

import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

/**
 * @author ZhibinJiang on 2022/7/15
 */
@Configuration
public class RabbitConfig {
    @Bean
    public MessageConverter messageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @PostConstruct
    public void confirm(CorrelationData correlationData,boolean ack,String cause) {
        System.out.println("correlationData: " + correlationData);
        System.out.println("ack: " + ack);
        System.out.println("cause: " + cause);
    }
}
