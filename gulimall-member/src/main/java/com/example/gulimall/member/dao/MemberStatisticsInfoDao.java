package com.example.gulimall.member.dao;

import com.example.gulimall.common.dao.BaseDao;
import com.example.gulimall.member.entity.MemberStatisticsInfoEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * 会员统计信息
 *
 * @author ${author} sunlightcs@gmail.com
 * @since 1.0.0 2022-06-21
 */
@Mapper
public interface MemberStatisticsInfoDao extends BaseDao<MemberStatisticsInfoEntity> {
	
}