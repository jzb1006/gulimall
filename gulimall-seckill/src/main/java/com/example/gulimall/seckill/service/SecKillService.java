package com.example.gulimall.seckill.service;

import com.example.gulimall.seckill.dto.SeckillSkuRedisDto;

import java.util.List;

public interface SecKillService {
    void uploadSeckillSkuLatest3Days();

    List<SeckillSkuRedisDto> getCurrentSeckillSkus();

    SeckillSkuRedisDto getSeckillSkuInfo(Long skuId);

    String kill(String killId, String key, Integer num);
}
