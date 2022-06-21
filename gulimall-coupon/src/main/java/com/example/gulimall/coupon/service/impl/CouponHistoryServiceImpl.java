package com.example.gulimall.coupon.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.gulimall.common.service.impl.CrudServiceImpl;
import com.example.gulimall.coupon.dao.CouponHistoryDao;
import com.example.gulimall.coupon.dto.CouponHistoryDTO;
import com.example.gulimall.coupon.entity.CouponHistoryEntity;
import com.example.gulimall.coupon.service.CouponHistoryService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * 优惠券领取历史记录
 *
 * @author ${author} sunlightcs@gmail.com
 * @since 1.0.0 2022-06-21
 */
@Service
public class CouponHistoryServiceImpl extends CrudServiceImpl<CouponHistoryDao, CouponHistoryEntity, CouponHistoryDTO> implements CouponHistoryService {

    @Override
    public QueryWrapper<CouponHistoryEntity> getWrapper(Map<String, Object> params){
        String id = (String)params.get("id");

        QueryWrapper<CouponHistoryEntity> wrapper = new QueryWrapper<>();
        wrapper.eq(StringUtils.isNotBlank(id), "id", id);

        return wrapper;
    }


}