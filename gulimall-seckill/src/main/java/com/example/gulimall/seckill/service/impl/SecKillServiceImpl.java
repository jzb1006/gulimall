package com.example.gulimall.seckill.service.impl;

import com.alibaba.fastjson.JSON;
import com.example.gulimall.common.utils.Result;
import com.example.gulimall.seckill.dto.SeckillSessionDto;
import com.example.gulimall.seckill.dto.SeckillSkuRedisDto;
import com.example.gulimall.seckill.feign.CouponFeignService;
import com.example.gulimall.seckill.service.SecKillService;
import org.redisson.api.RSemaphore;
import org.redisson.api.RedissonClient;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.BoundHashOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Service
public class SecKillServiceImpl implements SecKillService {

    @Autowired
    CouponFeignService couponFeignService;

    @Autowired
    private RedissonClient redissonClient;

    @Autowired
    private StringRedisTemplate redisTemplate;


    //K: SESSION_CACHE_PREFIX + startTime + "_" + endTime
    //V: sessionId+"-"+skuId的List
    private final String SESSION_CACHE_PREFIX = "seckill:sessions:";

    //K: 固定值SECKILL_CHARE_PREFIX
    //V: hash，k为sessionId+"-"+skuId，v为对应的商品信息SeckillSkuRedisTo
    private final String SECKILL_CHARE_PREFIX = "seckill:skus";

    //K: SKU_STOCK_SEMAPHORE+商品随机码
    //V: 秒杀的库存件数
    private final String SKU_STOCK_SEMAPHORE = "seckill:stock:";    //+商品随机码

    @Override
    public void uploadSeckillSkuLatest3Days() {
        Result<List<SeckillSessionDto>> seckillSessionsIn3Days = couponFeignService.getSeckillSessionsIn3Days();
        if (seckillSessionsIn3Days.getCode() == 0) {
            List<SeckillSessionDto> data = seckillSessionsIn3Days.getData();
            if (data != null) {
                saveSecKillSession(data);
                saveSecKillSku(data);
            }
        }
    }

    @Override
    public List<SeckillSkuRedisDto> getCurrentSeckillSkus() {
        Set<String> keys = redisTemplate.keys(SESSION_CACHE_PREFIX + "*");
        long currentTime = System.currentTimeMillis();
        for (String key : keys) {
            String replace = key.replace(SESSION_CACHE_PREFIX, "");
            String[] split = replace.split("_");
            long startTime = Long.parseLong(split[0]);
            long endTime = Long.parseLong(split[1]);
            //当前秒杀活动处于有效期内
            if (currentTime > startTime && currentTime < endTime) {
                List<String> range = redisTemplate.opsForList().range(key, -100, 100);
                BoundHashOperations<String, Object, Object> ops = redisTemplate.boundHashOps(SECKILL_CHARE_PREFIX);
                List<SeckillSkuRedisDto> collect = range.stream().map(s -> {
                    String json = (String) ops.get(s);
                    SeckillSkuRedisDto redisTo = JSON.parseObject(json, SeckillSkuRedisDto.class);
                    return redisTo;
                }).collect(Collectors.toList());
                return collect;
            }
        }
        return null;
    }

    @Override
    public SeckillSkuRedisDto getSeckillSkuInfo(Long skuId) {
        BoundHashOperations<String, String, String> ops = redisTemplate.boundHashOps(SECKILL_CHARE_PREFIX);
        Set<String> keys = ops.keys();
        for (String key : keys) {
            if (Pattern.matches("\\d-" + skuId,key)) {
                String v = ops.get(key);
                SeckillSkuRedisDto redisTo = JSON.parseObject(v, SeckillSkuRedisDto.class);
                //当前商品参与秒杀活动
                if (redisTo!=null){
                    long current = System.currentTimeMillis();
                    //当前活动在有效期，暴露商品随机码返回
                    if (redisTo.getStartTime() < current && redisTo.getEndTime() > current) {
                        return redisTo;
                    }
                    redisTo.setRandomCode(null);
                    return redisTo;
                }
            }
        }
        return null;
    }

    private void saveSecKillSession(List<SeckillSessionDto> sessions) {
        sessions.stream().forEach(session -> {
            String key = SESSION_CACHE_PREFIX + session.getStartTime().getTime() + "_" + session.getEndTime().getTime();
            //当前活动信息未保存过
            if (!redisTemplate.hasKey(key)) {
                List<String> values = session.getRelations().stream()
                        .map(sku -> sku.getPromotionSessionId() + "-" + sku.getSkuId())
                        .collect(Collectors.toList());
                redisTemplate.opsForList().leftPushAll(key, values);
            }
        });
    }

    private void saveSecKillSku(List<SeckillSessionDto> sessions) {
        BoundHashOperations<String, Object, Object> ops = redisTemplate.boundHashOps(SECKILL_CHARE_PREFIX);
        sessions.stream().forEach(session -> {
            session.getRelations().stream().forEach(sku -> {
                String key = sku.getPromotionSessionId() + "-" + sku.getSkuId();
                if (!ops.hasKey(key)) {
                    SeckillSkuRedisDto redisTo = new SeckillSkuRedisDto();
                    //1. 保存SeckillSkuVo信息
                    BeanUtils.copyProperties(sku, redisTo);
                    //2. 保存开始结束时间
                    redisTo.setStartTime(session.getStartTime().getTime());
                    redisTo.setEndTime(session.getEndTime().getTime());
                    //3. 远程查询sku信息并保存
//                    R r = productFeignService.info(sku.getSkuId());
//                    if (r.getCode() == 0) {
//                        SkuInfoVo skuInfo = r.getData("skuInfo", new TypeReference<SkuInfoVo>() {
//                        });
//                        redisTo.setSkuInfoVo(skuInfo);
//                    }
                    //4. 生成商品随机码，防止恶意攻击
                    String token = UUID.randomUUID().toString().replace("-", "");
                    redisTo.setRandomCode(token);
                    //5. 序列化为json并保存
                    String jsonString = JSON.toJSONString(redisTo);
                    ops.put(key, jsonString);
                    //5. 使用库存作为Redisson信号量限制库存
                    RSemaphore semaphore = redissonClient.getSemaphore(SKU_STOCK_SEMAPHORE + token);
                    semaphore.trySetPermits(sku.getSeckillCount());
                }
            });
        });
    }
}
