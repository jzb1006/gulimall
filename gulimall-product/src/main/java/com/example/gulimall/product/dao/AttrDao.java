package com.example.gulimall.product.dao;

import com.example.gulimall.common.dao.BaseDao;
import com.example.gulimall.product.entity.AttrEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * 商品属性
 *
 * @author ${author} sunlightcs@gmail.com
 * @since 1.0.0 2022-06-21
 */
@Mapper
public interface AttrDao extends BaseDao<AttrEntity> {
	
}