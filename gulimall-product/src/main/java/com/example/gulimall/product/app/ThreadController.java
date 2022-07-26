package com.example.gulimall.product.app;

import com.example.gulimall.common.dto.es.SpuBoundsDTO;
import com.example.gulimall.product.feign.SearchFeignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ThreadPoolExecutor;

@RestController
@RequestMapping("/thread")
public class ThreadController {

    @Autowired
    ThreadPoolExecutor threadPoolExecutor;

    @Autowired
    SearchFeignService searchFeignService;

    @GetMapping("/test")
    public void test() {
        CompletableFuture.supplyAsync(() -> {
            System.out.println("开始执行");
            System.out.println("执行完成");
            SpuBoundsDTO spuBoundsDTO = new SpuBoundsDTO();
            spuBoundsDTO.setSpuId(1L);
            searchFeignService.test(spuBoundsDTO);
            return "执行完成";
        }, threadPoolExecutor).thenAccept(System.out::println);
    }
}
