package com.example.gulimall.order.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.example.gulimall.common.service.impl.CrudServiceImpl;
import com.example.gulimall.order.dao.OrderItemDao;
import com.example.gulimall.order.dto.OrderItemDTO;
import com.example.gulimall.order.entity.OrderItemEntity;
import com.example.gulimall.order.service.OrderItemService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.aop.framework.AopContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

/**
 * 订单项信息
 *
 * @author ${author} sunlightcs@gmail.com
 * @since 1.0.0 2022-06-21
 */
@Service
public class OrderItemServiceImpl extends CrudServiceImpl<OrderItemDao, OrderItemEntity, OrderItemDTO> implements OrderItemService {

    @Override
    public QueryWrapper<OrderItemEntity> getWrapper(Map<String, Object> params) {
        String id = (String) params.get("id");

        QueryWrapper<OrderItemEntity> wrapper = new QueryWrapper<>();
        wrapper.eq(StringUtils.isNotBlank(id), "id", id);

        return wrapper;
    }



    @Override
    //Isolation.REPEATABLE_READ mysql默认的事务隔离级别可重复读
    @Transactional(isolation = Isolation.REPEATABLE_READ)
    public Boolean insertOrderItem(Long orderId,String orderSn) {
        // 创建订单
        return this.insert(OrderItemEntity.builder().orderId(orderId)
                .orderSn(orderSn)
                .skuId(1L)
                .skuName("华为")
                .skuQuantity(2)
                .build());
        // 调用远程锁定库存

    }

    // 事务失效问题，同一个对象内事务方法互调失败，原因绕过了代理对象，事务使用的代理对象来控制的。
// 使用代理对象来调用事务方法，就可以解决事务失效问题。
// 开启@EnableAspectJAutoProxy注解，开启AOP功能，自动代理对象，自动注入事务。
    @Transactional(propagation = Propagation.REQUIRED, timeout = 30)
    public void test3() {
        OrderItemServiceImpl aopContext = (OrderItemServiceImpl) AopContext.currentProxy();
        System.out.println("test3");
        aopContext.test4(); // 此时test4使用的和test3同一个事务，3的所有设置都传递到4了，3回滚4就回滚
        aopContext.test5(); // 5是新的一条事务，3回滚，5不回滚
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void test4() {
        System.out.println("test4");
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void test5() {
        System.out.println("test4");
    }
}