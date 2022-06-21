package com.example.gulimall.product.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.example.gulimall.common.service.impl.CrudServiceImpl;
import com.example.gulimall.product.dao.AttrGroupDao;
import com.example.gulimall.product.dto.AttrGroupDTO;
import com.example.gulimall.product.entity.AttrGroupEntity;
import com.example.gulimall.product.service.AttrGroupService;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * 属性分组
 *
 * @author ${author} sunlightcs@gmail.com
 * @since 1.0.0 2022-06-21
 */
@Service
public class AttrGroupServiceImpl extends CrudServiceImpl<AttrGroupDao, AttrGroupEntity, AttrGroupDTO> implements AttrGroupService {

    @Override
    public QueryWrapper<AttrGroupEntity> getWrapper(Map<String, Object> params){
        String id = (String)params.get("id");

        QueryWrapper<AttrGroupEntity> wrapper = new QueryWrapper<>();
        wrapper.eq(StringUtils.isNotBlank(id), "id", id);

        return wrapper;
    }


}