package com.example.gulimall.coupon.dao;

import com.example.gulimall.common.dao.BaseDao;
import com.example.gulimall.coupon.entity.CouponEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * 优惠券信息
 *
 * @author ${author} sunlightcs@gmail.com
 * @since 1.0.0 2022-06-21
 */
@Mapper
public interface CouponDao extends BaseDao<CouponEntity> {
	
}