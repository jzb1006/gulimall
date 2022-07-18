package com.example.gulimall.order.listener;

import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.example.gulimall.order.dto.OrderDTO;
import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

/**
 * @author ZhibinJiang on 2022/7/15
 */
@Service
@RabbitListener(queues={"hello-java-queue"})
public class ListenerTest {


    @RabbitHandler
    public void test(Message message, OrderDTO orderDTO, Channel channel) throws Exception {
        System.out.println("message: " + message);
        System.out.println("orderDTO: " + orderDTO);
    }

    @RabbitHandler
    public void test2(Message message, OrderItem orderItem, Channel channel) throws Exception {
        System.out.println("message: " + message);
        System.out.println("orderItem: " + orderItem);
    }
}
