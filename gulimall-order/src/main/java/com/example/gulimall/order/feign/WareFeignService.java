package com.example.gulimall.order.feign;

import com.example.gulimall.common.utils.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient("gulimall-ware")
public interface WareFeignService {

    @GetMapping("/ware/wareinfo/save")
    Result<Boolean> save();
}
