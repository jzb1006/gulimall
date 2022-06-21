package com.example.gulimall.coupon.dao;

import com.example.gulimall.common.dao.BaseDao;
import com.example.gulimall.coupon.entity.SkuFullReductionEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * 商品满减信息
 *
 * @author ${author} sunlightcs@gmail.com
 * @since 1.0.0 2022-06-21
 */
@Mapper
public interface SkuFullReductionDao extends BaseDao<SkuFullReductionEntity> {
	
}