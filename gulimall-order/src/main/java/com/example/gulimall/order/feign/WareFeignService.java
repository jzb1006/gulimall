package com.example.gulimall.order.feign;

import com.example.gulimall.common.utils.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient("gulimall-ware")
public interface WareFeignService {
    @GetMapping("/ware/waresku/lock/{skuId}/{num}/{wareId}")
    Result<Long> lockWareSku(@PathVariable("skuId") Long skuId, @PathVariable("num") Integer num, @PathVariable("wareId") Long wareId);
}
