package com.example.gulimall.auth.controller;

import com.example.gulimall.auth.feign.ThirdPartDeignService;
import com.example.gulimall.common.constant.AuthServiceConstant;
import com.example.gulimall.common.exception.BizCodeEnume;
import com.example.gulimall.common.exception.ErrorCode;
import com.example.gulimall.common.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

@RestController
public class LoginController {

    @Autowired
    ThirdPartDeignService thirdPartDeignService;

    @Autowired
    StringRedisTemplate redisTemplate;

    @GetMapping("sms/send-code")
    public Result<Boolean> sendCode(@RequestParam("phone") String phone) {
        String key = AuthServiceConstant.SMS_CODE_CACHE_PREFIX + phone;
        String redisCode = redisTemplate.opsForValue().get(key);
        if (redisCode != null) {
            long l = Long.parseLong(redisCode.split("_")[1]);
            if (System.currentTimeMillis() - l < 60 * 1000) {
                return new Result<Boolean>().error(BizCodeEnume.SMS_CODE_EXCEPTION.getCode(), BizCodeEnume.SMS_CODE_EXCEPTION.getMsg());
            }
        }

        String code = UUID.randomUUID().toString().substring(0, 6);

        redisTemplate.opsForValue().set(key, code + "_" + System.currentTimeMillis(), 10, TimeUnit.MINUTES);
        thirdPartDeignService.sendSms(phone, code);
        return new Result<Boolean>().ok(true);
    }

}
