package com.example.gulimall.product.app;

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

    @GetMapping("/test")
    public void test() {
        CompletableFuture.supplyAsync(() -> {
            System.out.println("开始执行");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("执行完成");
            return "执行完成";
        }, threadPoolExecutor).thenAccept(System.out::println);
    }
}
