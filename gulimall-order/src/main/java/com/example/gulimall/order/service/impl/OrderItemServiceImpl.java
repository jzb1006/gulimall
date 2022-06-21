package com.example.gulimall.order.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.gulimall.common.service.impl.CrudServiceImpl;
import com.example.gulimall.order.dao.OrderItemDao;
import com.example.gulimall.order.dto.OrderItemDTO;
import com.example.gulimall.order.entity.OrderItemEntity;
import com.example.gulimall.order.service.OrderItemService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

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
    public QueryWrapper<OrderItemEntity> getWrapper(Map<String, Object> params){
        String id = (String)params.get("id");

        QueryWrapper<OrderItemEntity> wrapper = new QueryWrapper<>();
        wrapper.eq(StringUtils.isNotBlank(id), "id", id);

        return wrapper;
    }


}