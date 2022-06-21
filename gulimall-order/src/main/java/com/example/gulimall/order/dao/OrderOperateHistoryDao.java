package com.example.gulimall.order.dao;

import com.example.gulimall.common.dao.BaseDao;
import com.example.gulimall.order.entity.OrderOperateHistoryEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * 订单操作历史记录
 *
 * @author ${author} sunlightcs@gmail.com
 * @since 1.0.0 2022-06-21
 */
@Mapper
public interface OrderOperateHistoryDao extends BaseDao<OrderOperateHistoryEntity> {
	
}