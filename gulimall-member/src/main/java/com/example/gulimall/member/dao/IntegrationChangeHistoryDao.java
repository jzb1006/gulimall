package com.example.gulimall.member.dao;

import com.example.gulimall.common.dao.BaseDao;
import com.example.gulimall.member.entity.IntegrationChangeHistoryEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * 积分变化历史记录
 *
 * @author ${author} sunlightcs@gmail.com
 * @since 1.0.0 2022-06-21
 */
@Mapper
public interface IntegrationChangeHistoryDao extends BaseDao<IntegrationChangeHistoryEntity> {
	
}