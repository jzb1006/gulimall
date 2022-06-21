package com.example.gulimall.member.dao;

import com.example.gulimall.common.dao.BaseDao;
import com.example.gulimall.member.entity.MemberCollectSubjectEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * 会员收藏的专题活动
 *
 * @author ${author} sunlightcs@gmail.com
 * @since 1.0.0 2022-06-21
 */
@Mapper
public interface MemberCollectSubjectDao extends BaseDao<MemberCollectSubjectEntity> {
	
}