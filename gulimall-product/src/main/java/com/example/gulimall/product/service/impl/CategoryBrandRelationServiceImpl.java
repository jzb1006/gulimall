package com.example.gulimall.product.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.gulimall.common.service.impl.CrudServiceImpl;
import com.example.gulimall.product.dao.CategoryBrandRelationDao;
import com.example.gulimall.product.dto.CategoryBrandRelationDTO;
import com.example.gulimall.product.entity.CategoryBrandRelationEntity;
import com.example.gulimall.product.service.CategoryBrandRelationService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * 品牌分类关联
 *
 * @author ${author} sunlightcs@gmail.com
 * @since 1.0.0 2022-06-21
 */
@Service
public class CategoryBrandRelationServiceImpl extends CrudServiceImpl<CategoryBrandRelationDao, CategoryBrandRelationEntity, CategoryBrandRelationDTO> implements CategoryBrandRelationService {

    @Override
    public QueryWrapper<CategoryBrandRelationEntity> getWrapper(Map<String, Object> params){
        String id = (String)params.get("id");

        QueryWrapper<CategoryBrandRelationEntity> wrapper = new QueryWrapper<>();
        wrapper.eq(StringUtils.isNotBlank(id), "id", id);

        return wrapper;
    }


}