package com.example.gulimall.order.service;

import com.example.gulimall.common.service.CrudService;
import com.example.gulimall.order.dto.OrderDTO;
import com.example.gulimall.order.entity.OrderEntity;

/**
 * 订单
 *
 * @author ${author} sunlightcs@gmail.com
 * @since 1.0.0 2022-06-21
 */
public interface OrderService extends CrudService<OrderEntity, OrderDTO> {

    Boolean submitOrder();

    void closeOrder(OrderEntity orderEntity);

}