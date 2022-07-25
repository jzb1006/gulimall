package com.example.gulimall.product.feign;

import com.example.gulimall.common.dto.SpuBoundDto;
import com.example.gulimall.common.utils.Result;
import lombok.extern.apachecommons.CommonsLog;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @author ZhibinJiang on 2022/6/27
 */
@FeignClient("gulimall-coupon")
public interface CouponFeignService {

    @PostMapping("coupon/spubounds")
    Result save(@RequestBody SpuBoundDto dto);
}
