package com.example.gulimall.order.config;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Configuration
public class RabbitConfirm {

    @Autowired
    RabbitTemplate rabbitTemplate;

    @PostConstruct
    public void init() {
        // 设置确认回调
        rabbitTemplate.setConfirmCallback((correlationData, ack, cause) -> {
            System.out.println("消息发送成功: " + correlationData);
            System.out.println("消息发送成功: " + ack);
            System.out.println("消息发送成功: " + cause);
        });

        //设置消息抵达队列的确认 setReturnCallback 只要是消息没有到达队列就触发这个回调
        rabbitTemplate.setReturnCallback((message, replyCode, replyText, exchange, routingKey) -> {
            System.out.println("消息抵达队列失败: " + message);
            System.out.println("消息抵达队列失败: " + replyCode);
            System.out.println("消息抵达队列失败: " + replyText);
            System.out.println("消息抵达队列失败: " + exchange);
            System.out.println("消息抵达队列失败: " + routingKey);
        });
    }
}
