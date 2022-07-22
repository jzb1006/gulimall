package com.example.gulimall.order.controller;

import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.example.gulimall.order.dto.OrderDTO;
import com.example.gulimall.order.entity.OrderEntity;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
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

    @GetMapping("/create-order")
    public String sendOrder() {
        // 模拟创建订单
        OrderEntity orderEntity = new OrderEntity();

        orderEntity.setOrderSn(IdWorker.getTimeId());
        orderEntity.setModifyTime(new Date());
        //给队列发送消息
        rabbitTemplate.convertAndSend("order-event-exchange", "order.create.order", orderEntity);
        return "ok";
    }
}
