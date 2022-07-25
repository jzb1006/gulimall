package com.example.gulimall.ware.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.*;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;

/**
 * @author ZhibinJiang on 2022/7/15
 */
@Slf4j
@Configuration
public class RabbitConfig {

    /**
     * 系列化mq消息
     *
     * @return
     */
    @Bean
    public MessageConverter messageConverter() {
        return new Jackson2JsonMessageConverter();
    }



    /**
     * 创建库存交换机
     */
    @Bean
    public Exchange stockExchange() {
        return new TopicExchange("stock-event-exchange", true, false);
    }

    /**
     * 创建普通库存队列
     */
    @Bean
    public Queue stockReleaseStockQueue() {
        return new Queue("stock.release.stock.queue", true, false, false);
    }

    /**
     * 创建死信延时库存队列
     */
    @Bean
    public Queue stoDelayQueue() {
        HashMap<String, Object> argument = new HashMap<>();
        argument.put("x-dead-letter-exchange", "stock-event-exchange");
        argument.put("x-dead-letter-routing-key", "stock.release");
        argument.put("x-message-ttl", 120000);
        return new Queue("stock.delay.queue", true, false, false, argument);
    }

    /**
     * 创建普通队列绑定关系
     */
    @Bean
    public Binding stockReleaseStockBinding() {
        return new Binding("stock.release.stock.queue",
                Binding.DestinationType.QUEUE,
                "stock-event-exchange",
                "stock.release.#", null);
    }


    /**
     * 创建死信队列绑定关系
     */
    @Bean
    public Binding stockDelayBinding() {
        return new Binding("stock.delay.queue",
                Binding.DestinationType.QUEUE,
                "stock-event-exchange",
                "stock.locked", null);
    }


}
