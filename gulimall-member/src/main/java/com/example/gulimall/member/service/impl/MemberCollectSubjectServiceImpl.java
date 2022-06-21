package com.example.gulimall.member.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.gulimall.common.service.impl.CrudServiceImpl;
import com.example.gulimall.member.dao.MemberCollectSubjectDao;
import com.example.gulimall.member.dto.MemberCollectSubjectDTO;
import com.example.gulimall.member.entity.MemberCollectSubjectEntity;
import com.example.gulimall.member.service.MemberCollectSubjectService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * 会员收藏的专题活动
 *
 * @author ${author} sunlightcs@gmail.com
 * @since 1.0.0 2022-06-21
 */
@Service
public class MemberCollectSubjectServiceImpl extends CrudServiceImpl<MemberCollectSubjectDao, MemberCollectSubjectEntity, MemberCollectSubjectDTO> implements MemberCollectSubjectService {

    @Override
    public QueryWrapper<MemberCollectSubjectEntity> getWrapper(Map<String, Object> params){
        String id = (String)params.get("id");

        QueryWrapper<MemberCollectSubjectEntity> wrapper = new QueryWrapper<>();
        wrapper.eq(StringUtils.isNotBlank(id), "id", id);

        return wrapper;
    }


}