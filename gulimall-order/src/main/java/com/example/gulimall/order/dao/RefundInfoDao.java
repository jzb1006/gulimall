package com.example.gulimall.order.dao;

import com.example.gulimall.common.dao.BaseDao;
import com.example.gulimall.order.entity.RefundInfoEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * 退款信息
 *
 * @author ${author} sunlightcs@gmail.com
 * @since 1.0.0 2022-06-21
 */
@Mapper
public interface RefundInfoDao extends BaseDao<RefundInfoEntity> {
	
}