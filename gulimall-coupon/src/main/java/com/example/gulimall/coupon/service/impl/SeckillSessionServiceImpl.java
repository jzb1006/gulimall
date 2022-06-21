package com.example.gulimall.coupon.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.gulimall.common.service.impl.CrudServiceImpl;
import com.example.gulimall.coupon.dao.SeckillSessionDao;
import com.example.gulimall.coupon.dto.SeckillSessionDTO;
import com.example.gulimall.coupon.entity.SeckillSessionEntity;
import com.example.gulimall.coupon.service.SeckillSessionService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * 秒杀活动场次
 *
 * @author ${author} sunlightcs@gmail.com
 * @since 1.0.0 2022-06-21
 */
@Service
public class SeckillSessionServiceImpl extends CrudServiceImpl<SeckillSessionDao, SeckillSessionEntity, SeckillSessionDTO> implements SeckillSessionService {

    @Override
    public QueryWrapper<SeckillSessionEntity> getWrapper(Map<String, Object> params){
        String id = (String)params.get("id");

        QueryWrapper<SeckillSessionEntity> wrapper = new QueryWrapper<>();
        wrapper.eq(StringUtils.isNotBlank(id), "id", id);

        return wrapper;
    }


}