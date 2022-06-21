package com.example.gulimall.ware.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.gulimall.common.service.impl.CrudServiceImpl;
import com.example.gulimall.ware.dao.WareSkuDao;
import com.example.gulimall.ware.dto.WareSkuDTO;
import com.example.gulimall.ware.entity.WareSkuEntity;
import com.example.gulimall.ware.service.WareSkuService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * 商品库存
 *
 * @author ${author} sunlightcs@gmail.com
 * @since 1.0.0 2022-06-21
 */
@Service
public class WareSkuServiceImpl extends CrudServiceImpl<WareSkuDao, WareSkuEntity, WareSkuDTO> implements WareSkuService {

    @Override
    public QueryWrapper<WareSkuEntity> getWrapper(Map<String, Object> params){
        String id = (String)params.get("id");

        QueryWrapper<WareSkuEntity> wrapper = new QueryWrapper<>();
        wrapper.eq(StringUtils.isNotBlank(id), "id", id);

        return wrapper;
    }


}