package com.example.gulimall.seckill.scheduled;

import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@EnableScheduling
@Component
@EnableAsync
public class Test {

    @Scheduled(cron = "0/2 * * * * ?")
    public void test() {
        System.out.println("test");
        // sleep 3 seconds
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
