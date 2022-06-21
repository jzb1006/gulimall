package com.example.gulimall.member.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.gulimall.common.service.impl.CrudServiceImpl;
import com.example.gulimall.member.dao.IntegrationChangeHistoryDao;
import com.example.gulimall.member.dto.IntegrationChangeHistoryDTO;
import com.example.gulimall.member.entity.IntegrationChangeHistoryEntity;
import com.example.gulimall.member.service.IntegrationChangeHistoryService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * 积分变化历史记录
 *
 * @author ${author} sunlightcs@gmail.com
 * @since 1.0.0 2022-06-21
 */
@Service
public class IntegrationChangeHistoryServiceImpl extends CrudServiceImpl<IntegrationChangeHistoryDao, IntegrationChangeHistoryEntity, IntegrationChangeHistoryDTO> implements IntegrationChangeHistoryService {

    @Override
    public QueryWrapper<IntegrationChangeHistoryEntity> getWrapper(Map<String, Object> params){
        String id = (String)params.get("id");

        QueryWrapper<IntegrationChangeHistoryEntity> wrapper = new QueryWrapper<>();
        wrapper.eq(StringUtils.isNotBlank(id), "id", id);

        return wrapper;
    }


}