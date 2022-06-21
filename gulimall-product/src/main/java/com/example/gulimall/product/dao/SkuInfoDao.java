package com.example.gulimall.product.dao;

import com.example.gulimall.common.dao.BaseDao;
import com.example.gulimall.product.entity.SkuInfoEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * sku信息
 *
 * @author ${author} sunlightcs@gmail.com
 * @since 1.0.0 2022-06-21
 */
@Mapper
public interface SkuInfoDao extends BaseDao<SkuInfoEntity> {
	
}