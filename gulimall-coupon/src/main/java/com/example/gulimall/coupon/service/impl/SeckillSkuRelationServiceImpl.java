package com.example.gulimall.coupon.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.gulimall.common.service.impl.CrudServiceImpl;
import com.example.gulimall.coupon.dao.SeckillSkuRelationDao;
import com.example.gulimall.coupon.dto.SeckillSkuRelationDTO;
import com.example.gulimall.coupon.entity.SeckillSkuRelationEntity;
import com.example.gulimall.coupon.service.SeckillSkuRelationService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * 秒杀活动商品关联
 *
 * @author ${author} sunlightcs@gmail.com
 * @since 1.0.0 2022-06-21
 */
@Service
public class SeckillSkuRelationServiceImpl extends CrudServiceImpl<SeckillSkuRelationDao, SeckillSkuRelationEntity, SeckillSkuRelationDTO> implements SeckillSkuRelationService {

    @Override
    public QueryWrapper<SeckillSkuRelationEntity> getWrapper(Map<String, Object> params){
        String id = (String)params.get("id");

        QueryWrapper<SeckillSkuRelationEntity> wrapper = new QueryWrapper<>();
        wrapper.eq(StringUtils.isNotBlank(id), "id", id);

        return wrapper;
    }


}