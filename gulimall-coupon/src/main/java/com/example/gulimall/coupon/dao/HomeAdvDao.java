package com.example.gulimall.coupon.dao;

import com.example.gulimall.common.dao.BaseDao;
import com.example.gulimall.coupon.entity.HomeAdvEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * 首页轮播广告
 *
 * @author ${author} sunlightcs@gmail.com
 * @since 1.0.0 2022-06-21
 */
@Mapper
public interface HomeAdvDao extends BaseDao<HomeAdvEntity> {
	
}