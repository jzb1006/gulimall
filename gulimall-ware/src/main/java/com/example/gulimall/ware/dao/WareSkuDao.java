package com.example.gulimall.ware.dao;

import com.example.gulimall.common.dao.BaseDao;
import com.example.gulimall.ware.entity.WareSkuEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * 商品库存
 *
 * @author ${author} sunlightcs@gmail.com
 * @since 1.0.0 2022-06-21
 */
@Mapper
public interface WareSkuDao extends BaseDao<WareSkuEntity> {
	
}