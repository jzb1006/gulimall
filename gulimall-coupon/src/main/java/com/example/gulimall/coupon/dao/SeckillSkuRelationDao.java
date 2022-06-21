package com.example.gulimall.coupon.dao;

import com.example.gulimall.common.dao.BaseDao;
import com.example.gulimall.coupon.entity.SeckillSkuRelationEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * 秒杀活动商品关联
 *
 * @author ${author} sunlightcs@gmail.com
 * @since 1.0.0 2022-06-21
 */
@Mapper
public interface SeckillSkuRelationDao extends BaseDao<SeckillSkuRelationEntity> {
	
}