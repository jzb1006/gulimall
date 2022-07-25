package com.example.gulimall.product.feign;

import com.example.gulimall.common.dto.es.SpuBoundsDTO;
import com.example.gulimall.common.utils.Result;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author ZhibinJiang on 2022/6/29
 */
@SpringBootTest
class SearchFeignServiceTest {

    @Autowired
    SearchFeignService service;

    @Test
    void productSave() {
        SpuBoundsDTO spuBoundsDTO = new SpuBoundsDTO();
        spuBoundsDTO.setSpuId(1L);
        Result<Boolean> test = service.test(spuBoundsDTO);
        System.out.println(test);
    }
}