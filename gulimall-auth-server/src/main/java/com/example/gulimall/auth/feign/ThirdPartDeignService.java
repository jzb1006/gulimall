package com.example.gulimall.auth.feign;

import com.example.gulimall.common.utils.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient("gulimall-third-party")
public interface ThirdPartDeignService {
    @GetMapping("/sms/send")
    Result<Boolean> sendSms(@RequestParam("phone") String phone, @RequestParam("code") String code);
}
