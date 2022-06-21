package com.example.gulimall.coupon.dao;

import com.example.gulimall.common.dao.BaseDao;
import com.example.gulimall.coupon.entity.SeckillSessionEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * 秒杀活动场次
 *
 * @author ${author} sunlightcs@gmail.com
 * @since 1.0.0 2022-06-21
 */
@Mapper
public interface SeckillSessionDao extends BaseDao<SeckillSessionEntity> {
	
}