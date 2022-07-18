package com.example.gulimall.order;

import java.math.BigDecimal;
import java.util.Date;
import java.util.UUID;

import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.example.gulimall.order.dto.OrderDTO;
import com.example.gulimall.order.dto.OrderReturnApplyDTO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@Slf4j
@SpringBootTest
class GulimallOrderApplicationTests {

    @Autowired
    AmqpAdmin amqpAdmin;

    @Autowired
    RabbitTemplate rabbitTemplate;

    @Test
    void createExchange() {
        DirectExchange directExchange = new DirectExchange("hello-java-exchange", true, false);
        amqpAdmin.declareExchange(directExchange);
        log.info("directExchange: {}", directExchange);
    }

    @Test
    void createQueue() {
        Queue queue = new Queue("hello-java-queue", true, false, false);
        amqpAdmin.declareQueue(queue);
        log.info("queue: {}", queue);
    }

    @Test
    void createBinding() {
        Binding binding = new Binding("hello-java-queue", Binding.DestinationType.QUEUE, "hello-java-exchange", "hello.java", null);
        amqpAdmin.declareBinding(binding);
    }

    /**
     * send message to queue
     */
    @Test
    void sendMessage() {
        for (int i = 0; i < 10; i++) {
            if (i % 2 == 0) {
                OrderDTO orderDTO = new OrderDTO();
                orderDTO.setId((long) i);

                rabbitTemplate.convertAndSend("hello-java-exchange", "hello.java", orderDTO, new CorrelationData(UUID.randomUUID().toString()));
            } else {
                OrderItem orderItem = new OrderItem();
                orderItem.setColumn(String.valueOf(i));
                rabbitTemplate.convertAndSend("hello-java-exchange", "hello2.java", orderItem, new CorrelationData(UUID.randomUUID().toString()));
            }
        }
    }

}
