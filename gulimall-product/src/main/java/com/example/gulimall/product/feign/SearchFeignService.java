package com.example.gulimall.product.feign;

import com.example.gulimall.common.dto.es.SpuBoundsDTO;
import com.example.gulimall.common.utils.Result;
import com.example.gulimall.product.dto.SkuModelDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * @author ZhibinJiang on 2022/6/29
 */
@FeignClient("gulimall-search")
public interface SearchFeignService {

    @PostMapping("search/save/product")
    Result<Boolean> productSave(@RequestBody List<SkuModelDto> skuModelList);

    @PostMapping("search/save/test")
    Result<Boolean> test(@RequestBody SpuBoundsDTO skuModel);
}
