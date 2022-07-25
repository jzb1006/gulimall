package com.example.gulimall.search.controller;

import com.example.gulimall.common.dto.es.SpuBoundsDTO;
import com.example.gulimall.common.dto.es.SkuModel;
import com.example.gulimall.common.exception.BizCodeEnume;
import com.example.gulimall.common.utils.Result;
import com.example.gulimall.search.service.ProductSaveService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

/**
 * @author ZhibinJiang on 2022/6/29
 */
@Slf4j
@RestController
@RequestMapping("search/save")
public class ElasticSaveController {

    @Autowired
    ProductSaveService productSaveService;

    @PostMapping("product")
    public Result<Boolean> productSave(@RequestBody List<SkuModel> skuModelList){
        try {
            System.out.println("=======");
            System.out.println(skuModelList);
            Boolean save = productSaveService.save(skuModelList);
            return new Result<Boolean>().ok(save);
        } catch (IOException e) {
            log.error("商品上架失败:{}",e.getMessage());
            return new Result<Boolean>().error(BizCodeEnume.PRODUCT_UP_EXCEPTION.getCode(),BizCodeEnume.PRODUCT_UP_EXCEPTION.getMsg());
        }
    }

    @PostMapping("test")
    public void productSave(@RequestBody SpuBoundsDTO skuModel){
        System.out.println(skuModel);
    }

    @GetMapping("test")
    public void test(){
        System.out.println("test");
    }
}
