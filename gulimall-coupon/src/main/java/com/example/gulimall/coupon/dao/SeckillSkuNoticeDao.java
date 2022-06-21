package com.example.gulimall.coupon.dao;

import com.example.gulimall.common.dao.BaseDao;
import com.example.gulimall.coupon.entity.SeckillSkuNoticeEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * 秒杀商品通知订阅
 *
 * @author ${author} sunlightcs@gmail.com
 * @since 1.0.0 2022-06-21
 */
@Mapper
public interface SeckillSkuNoticeDao extends BaseDao<SeckillSkuNoticeEntity> {
	
}