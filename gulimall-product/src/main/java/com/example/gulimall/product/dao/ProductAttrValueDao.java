package com.example.gulimall.product.dao;

import com.example.gulimall.common.dao.BaseDao;
import com.example.gulimall.product.entity.ProductAttrValueEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * spu属性值
 *
 * @author ${author} sunlightcs@gmail.com
 * @since 1.0.0 2022-06-21
 */
@Mapper
public interface ProductAttrValueDao extends BaseDao<ProductAttrValueEntity> {
	
}