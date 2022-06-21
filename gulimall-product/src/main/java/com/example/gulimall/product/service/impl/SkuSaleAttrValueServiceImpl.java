package com.example.gulimall.product.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.gulimall.common.service.impl.CrudServiceImpl;
import com.example.gulimall.product.dao.SkuSaleAttrValueDao;
import com.example.gulimall.product.dto.SkuSaleAttrValueDTO;
import com.example.gulimall.product.entity.SkuSaleAttrValueEntity;
import com.example.gulimall.product.service.SkuSaleAttrValueService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * sku销售属性&值
 *
 * @author ${author} sunlightcs@gmail.com
 * @since 1.0.0 2022-06-21
 */
@Service
public class SkuSaleAttrValueServiceImpl extends CrudServiceImpl<SkuSaleAttrValueDao, SkuSaleAttrValueEntity, SkuSaleAttrValueDTO> implements SkuSaleAttrValueService {

    @Override
    public QueryWrapper<SkuSaleAttrValueEntity> getWrapper(Map<String, Object> params){
        String id = (String)params.get("id");

        QueryWrapper<SkuSaleAttrValueEntity> wrapper = new QueryWrapper<>();
        wrapper.eq(StringUtils.isNotBlank(id), "id", id);

        return wrapper;
    }


}