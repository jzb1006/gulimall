package com.example.gulimall.order.dao;

import com.example.gulimall.common.dao.BaseDao;
import com.example.gulimall.order.entity.OrderReturnReasonEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * 退货原因
 *
 * @author ${author} sunlightcs@gmail.com
 * @since 1.0.0 2022-06-21
 */
@Mapper
public interface OrderReturnReasonDao extends BaseDao<OrderReturnReasonEntity> {
	
}