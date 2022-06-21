package com.example.gulimall.order.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.gulimall.common.service.impl.CrudServiceImpl;
import com.example.gulimall.order.dao.OrderReturnApplyDao;
import com.example.gulimall.order.dto.OrderReturnApplyDTO;
import com.example.gulimall.order.entity.OrderReturnApplyEntity;
import com.example.gulimall.order.service.OrderReturnApplyService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * 订单退货申请
 *
 * @author ${author} sunlightcs@gmail.com
 * @since 1.0.0 2022-06-21
 */
@Service
public class OrderReturnApplyServiceImpl extends CrudServiceImpl<OrderReturnApplyDao, OrderReturnApplyEntity, OrderReturnApplyDTO> implements OrderReturnApplyService {

    @Override
    public QueryWrapper<OrderReturnApplyEntity> getWrapper(Map<String, Object> params){
        String id = (String)params.get("id");

        QueryWrapper<OrderReturnApplyEntity> wrapper = new QueryWrapper<>();
        wrapper.eq(StringUtils.isNotBlank(id), "id", id);

        return wrapper;
    }


}