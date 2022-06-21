package com.example.gulimall.coupon.dao;

import com.example.gulimall.common.dao.BaseDao;
import com.example.gulimall.coupon.entity.CouponHistoryEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * 优惠券领取历史记录
 *
 * @author ${author} sunlightcs@gmail.com
 * @since 1.0.0 2022-06-21
 */
@Mapper
public interface CouponHistoryDao extends BaseDao<CouponHistoryEntity> {
	
}