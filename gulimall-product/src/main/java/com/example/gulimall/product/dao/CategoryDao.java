package com.example.gulimall.product.dao;

import com.example.gulimall.common.dao.BaseDao;
import com.example.gulimall.product.entity.CategoryEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * 商品三级分类
 *
 * @author ${author} sunlightcs@gmail.com
 * @since 1.0.0 2022-06-21
 */
@Mapper
public interface CategoryDao extends BaseDao<CategoryEntity> {
	
}