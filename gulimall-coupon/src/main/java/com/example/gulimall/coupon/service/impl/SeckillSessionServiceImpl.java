package com.example.gulimall.coupon.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.gulimall.common.service.impl.CrudServiceImpl;
import com.example.gulimall.coupon.dao.SeckillSessionDao;
import com.example.gulimall.coupon.dto.SeckillSessionDTO;
import com.example.gulimall.coupon.dto.SeckillSkuRelationDTO;
import com.example.gulimall.coupon.entity.SeckillSessionEntity;
import com.example.gulimall.coupon.entity.SeckillSkuRelationEntity;
import com.example.gulimall.coupon.service.SeckillSessionService;
import com.example.gulimall.coupon.service.SeckillSkuRelationService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 秒杀活动场次
 *
 * @author ${author} sunlightcs@gmail.com
 * @since 1.0.0 2022-06-21
 */
@Service
public class SeckillSessionServiceImpl extends CrudServiceImpl<SeckillSessionDao, SeckillSessionEntity, SeckillSessionDTO> implements SeckillSessionService {

    @Autowired
    SeckillSkuRelationService seckillSkuRelationService;

    @Override
    public QueryWrapper<SeckillSessionEntity> getWrapper(Map<String, Object> params) {
        String id = (String) params.get("id");

        QueryWrapper<SeckillSessionEntity> wrapper = new QueryWrapper<>();
        wrapper.eq(StringUtils.isNotBlank(id), "id", id);

        return wrapper;
    }


    @Override
    public List<SeckillSessionEntity> getSeckillSessionsIn3Days() {
        QueryWrapper<SeckillSessionEntity> startTime = new QueryWrapper<SeckillSessionEntity>().between("start_time", getStartTime(), getEndTime());
        List<SeckillSessionEntity> seckillSessionEntities = this.baseDao.selectList(startTime);
        if (seckillSessionEntities.size() == 0) {
            return null;
        }

        seckillSessionEntities = seckillSessionEntities.stream().map(session -> {
            HashMap<String, Object> query = new HashMap<>();
            query.put("promotion_session_id", session.getId().toString());
            List<SeckillSkuRelationDTO> list = seckillSkuRelationService.list(query);
            session.setRelations(list);
            return session;
        }).collect(Collectors.toList());
        return seckillSessionEntities;
    }

    //当前天数的 00:00:00
    private String getStartTime() {
        LocalDate now = LocalDate.now();
        LocalDateTime time = now.atTime(LocalTime.MIN);
        String format = time.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        return format;
    }

    //当前天数+2 23:59:59..
    private String getEndTime() {
        LocalDate now = LocalDate.now();
        LocalDateTime time = now.plusDays(2).atTime(LocalTime.MAX);
        String format = time.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        return format;
    }
}