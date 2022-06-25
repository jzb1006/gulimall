package com.example.gulimall.product;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.gulimall.product.dto.BrandDTO;
import com.example.gulimall.product.entity.BrandEntity;
import com.example.gulimall.product.service.BrandService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootTest
class GulimallProductApplicationTests {

    @Autowired
    BrandService brandService;

    @Test
    void contextLoads() {
        BrandDTO brandDTO = new BrandDTO();
        brandDTO.setName("品牌1");
        Map<String, Object> map = new HashMap<>(16);
        map.put("name", "品牌1");
        List<BrandDTO> list = brandService.list(map);
        System.out.println(list);

    }



}
