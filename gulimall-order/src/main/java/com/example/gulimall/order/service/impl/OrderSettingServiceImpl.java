package com.example.gulimall.order.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.gulimall.common.service.impl.CrudServiceImpl;
import com.example.gulimall.order.dao.OrderSettingDao;
import com.example.gulimall.order.dto.OrderSettingDTO;
import com.example.gulimall.order.entity.OrderSettingEntity;
import com.example.gulimall.order.service.OrderSettingService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * 订单配置信息
 *
 * @author ${author} sunlightcs@gmail.com
 * @since 1.0.0 2022-06-21
 */
@Service
public class OrderSettingServiceImpl extends CrudServiceImpl<OrderSettingDao, OrderSettingEntity, OrderSettingDTO> implements OrderSettingService {

    @Override
    public QueryWrapper<OrderSettingEntity> getWrapper(Map<String, Object> params){
        String id = (String)params.get("id");

        QueryWrapper<OrderSettingEntity> wrapper = new QueryWrapper<>();
        wrapper.eq(StringUtils.isNotBlank(id), "id", id);

        return wrapper;
    }


}