package com.example.gulimall.order;

import java.math.BigDecimal;
import java.util.Date;
import java.util.UUID;

import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.example.gulimall.order.dao.OrderItemDao;
import com.example.gulimall.order.dto.OrderDTO;
import com.example.gulimall.order.dto.OrderReturnApplyDTO;
import com.example.gulimall.order.entity.OrderItemEntity;
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
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@SpringBootTest
class GulimallOrderApplicationTests {

    @Autowired
    AmqpAdmin amqpAdmin;

    @Autowired
    RabbitTemplate rabbitTemplate;

    @Autowired
    OrderItemDao orderItemDao;

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

    // test
    @Test
    void test() {
        // 测试订单号的生成
        String timeId = IdWorker.getTimeId();
        System.out.println(timeId);
    }

    // abs test
    @Test
    void testAbs() {
        BigDecimal bigDecimal = new BigDecimal("100.001");
        BigDecimal bigDecimal2 = new BigDecimal("100");
        if (Math.abs(bigDecimal.subtract(bigDecimal2).doubleValue()) < 0.01) {
            System.out.println("接受范围");
        } else {
            System.out.println("不接受范围");
        }
    }

    // test
    @Test
    @Transactional
    public void test2() {
        orderItemDao.insert(OrderItemEntity.builder()
                .orderId(12L)
                .build());
//        throw new RuntimeException("test");
    }

    @Transactional(propagation = Propagation.REQUIRED,timeout = 30)
    @Test
    public void test3() {
        System.out.println("test3");
        test4(); // 此时test4使用的和test3同一个事务，3的所有设置都传递到4了，3回滚4就回滚
        test5(); // 5是新的一条事务，3回滚，5不回滚
    }

    @Test
    @Transactional(propagation = Propagation.REQUIRED)
    public void test4() {
        System.out.println("test4");
    }

    @Test
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void test5() {
        System.out.println("test4");
    }

}
