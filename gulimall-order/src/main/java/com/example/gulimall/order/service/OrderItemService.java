package com.example.gulimall.order.service;

import com.example.gulimall.common.service.CrudService;
import com.example.gulimall.order.dto.OrderItemDTO;
import com.example.gulimall.order.entity.OrderItemEntity;

/**
 * 订单项信息
 *
 * @author ${author} sunlightcs@gmail.com
 * @since 1.0.0 2022-06-21
 */
public interface OrderItemService extends CrudService<OrderItemEntity, OrderItemDTO> {
    Boolean insertOrderItem(Long orderId,String orderSn);
}