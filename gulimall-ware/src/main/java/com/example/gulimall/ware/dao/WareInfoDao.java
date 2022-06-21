package com.example.gulimall.ware.dao;

import com.example.gulimall.common.dao.BaseDao;
import com.example.gulimall.ware.entity.WareInfoEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * 仓库信息
 *
 * @author ${author} sunlightcs@gmail.com
 * @since 1.0.0 2022-06-21
 */
@Mapper
public interface WareInfoDao extends BaseDao<WareInfoEntity> {
	
}