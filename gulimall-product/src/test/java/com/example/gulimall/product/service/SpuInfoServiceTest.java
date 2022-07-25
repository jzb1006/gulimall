package com.example.gulimall.product.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author ZhibinJiang on 2022/6/29
 */
@SpringBootTest
class SpuInfoServiceTest {

    @Autowired
    SpuInfoService spuInfoService;

    @Test
    void up() {
        spuInfoService.up(1L);
    }
}