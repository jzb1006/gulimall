package com.example.gulimall.coupon.feign;

import com.example.gulimall.common.page.PageData;
import com.example.gulimall.common.utils.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(value = "gulimall-product")
public interface ProductFeignService {
    @GetMapping("thread/test")
    void test();
}
