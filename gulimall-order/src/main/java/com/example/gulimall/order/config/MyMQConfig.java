package com.example.gulimall.order.config;

import com.example.gulimall.order.entity.OrderEntity;
import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.connection.CorrelationData;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;

@Slf4j
@Configuration
public class MyMQConfig {

//    @RabbitListener(queues = "order.release.order.queue")
//    public void listener(OrderEntity orderEntity, Channel channel, Message message) throws Exception {
//        log.info("接收过期订单消息，准备取消订单" + orderEntity.getOrderSn());
//
//        //确认送达
//        channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
//    }

    @Bean
    public Queue orderDelayQueue() {
        /**
         * 设置死信队列参数
         */
        HashMap<String, Object> argument = new HashMap<>();
        argument.put("x-dead-letter-exchange", "order-event-exchange");
        argument.put("x-dead-letter-routing-key", "order.release.order");
        argument.put("x-message-ttl", 60000);

        return new Queue("order.delay.queue", true, false, false, argument);
    }

    @Bean
    public Queue orderReleaseOrderQueue() {
        return new Queue("order.release.order.queue", true, false, false);
    }

    @Bean
    public Exchange orderEventExchange() {
        return new TopicExchange("order-event-exchange", true, false);
    }

    @Bean
    public Binding orderCreateOrderBinding() {
        return new Binding("order.delay.queue",
                Binding.DestinationType.QUEUE,
                "order-event-exchange",
                "order.create.order", null);
    }

    @Bean
    public Binding orderReleaseOrderBinding() {
        return new Binding("order.release.order.queue",
                Binding.DestinationType.QUEUE,
                "order-event-exchange",
                "order.release.order", null);
    }

    @Bean
    public Binding orderReleaseOtherBinding() {
        return new Binding("stock.release.stock.queue",
                Binding.DestinationType.QUEUE,
                "order-event-exchange",
                "order.release.other.#",
                null);
    }

}
