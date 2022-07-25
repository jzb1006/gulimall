package com.example.gulimall.product.feign;

import com.example.gulimall.common.dto.SpuBoundDto;
import com.example.gulimall.common.utils.Result;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author ZhibinJiang on 2022/6/27
 */
@SpringBootTest
class CouponFeignServiceTest {

    @Autowired
    CouponFeignService couponFeignService;

    @Test
    void save() {
        SpuBoundDto spuBoundDto = new SpuBoundDto();
        spuBoundDto.setBuyBounds(BigDecimal.valueOf(1.00));
        spuBoundDto.setGrowBounds(BigDecimal.valueOf(2.00));
        Result save = couponFeignService.save(spuBoundDto);
        System.out.println(save.getCode());
        System.out.println(save.getData());
        System.out.println(save.getMsg());
    }
}