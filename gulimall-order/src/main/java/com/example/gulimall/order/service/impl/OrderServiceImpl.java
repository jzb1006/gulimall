package com.example.gulimall.order.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.example.gulimall.common.service.impl.CrudServiceImpl;
import com.example.gulimall.order.dao.OrderDao;
import com.example.gulimall.order.dto.OrderDTO;
import com.example.gulimall.order.entity.OrderEntity;
import com.example.gulimall.order.feign.WareFeignService;
import com.example.gulimall.order.service.OrderItemService;
import com.example.gulimall.order.service.OrderService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Map;

/**
 * 订单
 *
 * @author ${author} sunlightcs@gmail.com
 * @since 1.0.0 2022-06-21
 */
@Service
public class OrderServiceImpl extends CrudServiceImpl<OrderDao, OrderEntity, OrderDTO> implements OrderService {

    @Autowired
    OrderItemService orderItemService;

    @Autowired
    WareFeignService wareFeignService;

    @Override
    public QueryWrapper<OrderEntity> getWrapper(Map<String, Object> params) {
        String id = (String) params.get("id");

        QueryWrapper<OrderEntity> wrapper = new QueryWrapper<>();
        wrapper.eq(StringUtils.isNotBlank(id), "id", id);

        return wrapper;
    }

    @Override
    @Transactional
    public Boolean submitOrder() {
        // 创建主订单
        OrderEntity order = OrderEntity.builder()
                .orderSn(IdWorker.getTimeId()).totalAmount(BigDecimal.valueOf(100)).status(0)
                .build();
        baseDao.insert(order);
        // 子订单创建
        orderItemService.insertOrderItem(order.getId(), order.getOrderSn());

        // 远程扣减库存
        wareFeignService.lockWareSku(1L, 2, 1L);
        return true;
    }


}