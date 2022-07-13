package com.example.gulimall.third.party.component;

import org.springframework.stereotype.Component;

@Component
public class SmsComponent {

    public void SendSms(String phone, String code) {
        System.out.println("发送短信 手机号"+phone+"，验证码："+code);
    }
}
