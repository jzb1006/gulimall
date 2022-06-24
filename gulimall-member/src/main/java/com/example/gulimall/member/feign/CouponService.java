package com.example.gulimall.member.feign;

import com.example.gulimall.common.utils.Result;
import io.swagger.annotations.ApiOperation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author ZhibinJiang on 2022/6/23
 */
@Component
@FeignClient(value = "gulimall-coupon")
public interface CouponService {

    @RequestMapping("coupon/coupon/test")
    Result<String> test();
}
