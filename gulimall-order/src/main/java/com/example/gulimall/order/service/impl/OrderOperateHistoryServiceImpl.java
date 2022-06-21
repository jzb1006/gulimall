package com.example.gulimall.order.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.gulimall.common.service.impl.CrudServiceImpl;
import com.example.gulimall.order.dao.OrderOperateHistoryDao;
import com.example.gulimall.order.dto.OrderOperateHistoryDTO;
import com.example.gulimall.order.entity.OrderOperateHistoryEntity;
import com.example.gulimall.order.service.OrderOperateHistoryService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * 订单操作历史记录
 *
 * @author ${author} sunlightcs@gmail.com
 * @since 1.0.0 2022-06-21
 */
@Service
public class OrderOperateHistoryServiceImpl extends CrudServiceImpl<OrderOperateHistoryDao, OrderOperateHistoryEntity, OrderOperateHistoryDTO> implements OrderOperateHistoryService {

    @Override
    public QueryWrapper<OrderOperateHistoryEntity> getWrapper(Map<String, Object> params){
        String id = (String)params.get("id");

        QueryWrapper<OrderOperateHistoryEntity> wrapper = new QueryWrapper<>();
        wrapper.eq(StringUtils.isNotBlank(id), "id", id);

        return wrapper;
    }


}