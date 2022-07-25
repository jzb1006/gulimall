package com.example.gulimall.seckill.feign;

import com.example.gulimall.common.utils.Result;
import com.example.gulimall.seckill.dto.SeckillSessionDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@FeignClient("gulimall-coupon")
public interface CouponFeignService {
    @RequestMapping("/coupon/seckillsession/getSeckillSessionsIn3Days")
    Result<List<SeckillSessionDto>> getSeckillSessionsIn3Days();
}
