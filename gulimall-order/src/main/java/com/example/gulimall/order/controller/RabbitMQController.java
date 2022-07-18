package com.example.gulimall.order.controller;

import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.example.gulimall.order.dto.OrderDTO;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/rabbit")
public class RabbitMQController {

    @Autowired
    RabbitTemplate rabbitTemplate;

    @GetMapping("/send")
    void sendMessage() {
        for (int i = 0; i < 2; i++) {
            if (i % 2 == 0) {
                OrderDTO orderDTO = new OrderDTO();
                orderDTO.setId((long) i);

                rabbitTemplate.convertAndSend("hello-java-exchange", "hello.java", orderDTO, new CorrelationData(UUID.randomUUID().toString()));
            } else {
                OrderItem orderItem = new OrderItem();
                orderItem.setColumn(String.valueOf(i));
                rabbitTemplate.convertAndSend("hello-java-exchange", "hello.java", orderItem, new CorrelationData(UUID.randomUUID().toString()));
            }
        }
    }
}
