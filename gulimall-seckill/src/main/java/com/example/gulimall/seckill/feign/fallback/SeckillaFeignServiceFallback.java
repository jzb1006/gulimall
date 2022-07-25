package com.example.gulimall.seckill.feign.fallback;

import com.example.gulimall.common.utils.Result;
import com.example.gulimall.seckill.dto.SeckillSessionDto;
import com.example.gulimall.seckill.feign.CouponFeignService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class SeckillaFeignServiceFallback implements CouponFeignService {
    @Override
    public Result<List<SeckillSessionDto>> getSeckillSessionsIn3Days() {
        log.error("熔断器触发，调用失败");
        return new Result<List<SeckillSessionDto>>().error(1000, "调用失败");
    }
}
