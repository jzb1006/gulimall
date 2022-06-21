package com.example.gulimall.product.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.gulimall.common.service.impl.CrudServiceImpl;
import com.example.gulimall.product.dao.BrandDao;
import com.example.gulimall.product.dto.BrandDTO;
import com.example.gulimall.product.entity.BrandEntity;
import com.example.gulimall.product.service.BrandService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * 品牌
 *
 * @author ${author} sunlightcs@gmail.com
 * @since 1.0.0 2022-06-21
 */
@Service
public class BrandServiceImpl extends CrudServiceImpl<BrandDao, BrandEntity, BrandDTO> implements BrandService {

    @Override
    public QueryWrapper<BrandEntity> getWrapper(Map<String, Object> params){
        String id = (String)params.get("id");

        QueryWrapper<BrandEntity> wrapper = new QueryWrapper<>();
        wrapper.eq(StringUtils.isNotBlank(id), "id", id);

        return wrapper;
    }


}