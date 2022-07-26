package com.example.gulimall.seckill.controller;

import com.example.gulimall.common.utils.Result;
import com.example.gulimall.seckill.dto.SeckillSkuRedisDto;
import com.example.gulimall.seckill.service.SecKillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/seckill")
public class SecKillController {

    @Autowired
    private SecKillService secKillService;

    /**
     * 当前时间可以参与秒杀的商品信息
     *
     * @return
     */
    @GetMapping(value = "/getCurrentSeckillSkus")
    public Result<List<SeckillSkuRedisDto>> getCurrentSeckillSkus() {
        //获取到当前可以参加秒杀商品的信息
        List<SeckillSkuRedisDto> vos = secKillService.getCurrentSeckillSkus();

        return new Result<List<SeckillSkuRedisDto>>().ok(vos);
    }

    @ResponseBody
    @GetMapping(value = "/getSeckillSkuInfo/{skuId}")
    public Result<SeckillSkuRedisDto> getSeckillSkuInfo(@PathVariable("skuId") Long skuId) {
        SeckillSkuRedisDto to = secKillService.getSeckillSkuInfo(skuId);
        return new Result<SeckillSkuRedisDto>().ok(to);
    }

    @GetMapping("/kill")
    public Result<String> kill(@RequestParam("killId") String killId,
                               @RequestParam("key") String key,
                               @RequestParam("num") Integer num) {
        String orderSn = null;
        orderSn = secKillService.kill(killId, key, num);
        System.out.println(orderSn);
        return new Result<String>().ok(orderSn);
    }

}
