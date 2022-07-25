package com.example.gulimall.third.party.controller;

import com.example.gulimall.common.utils.Result;
import com.example.gulimall.third.party.component.SmsComponent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.Request;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sms")
public class SendSmsController {
    @Autowired
    SmsComponent smsComponent;

    /**
     * 提供给别的服务调用
     *
     * @param phone
     * @param code
     */
    @GetMapping("/send")
    Result<Boolean> sendSms(@RequestParam("phone") String phone, @RequestParam("code") String code) {
        smsComponent.SendSms(phone, code);
        return new Result<Boolean>().ok(true);
    }


}
