package com.example.gulimall.product.app;

import lombok.extern.slf4j.Slf4j;
import org.redisson.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/redis")
@Slf4j
public class RedisController {

    @Autowired
    RedissonClient redissonClient;


    @GetMapping("/test")
    public void test() {
        RLock test = redissonClient.getLock("test-lock");
        test.lock(30, TimeUnit.SECONDS);
        try {
            log.info("加锁成功" + Thread.currentThread().getId());
            Thread.sleep(30000);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            log.info("解锁成功" + Thread.currentThread().getId());
            test.unlock();
        }
    }

    /**
     * 分布式写锁 -- 排他锁
     * @return
     */
    @GetMapping("/write")
    public String write() {
        String s = UUID.randomUUID().toString();
        RReadWriteLock readWriteLock = redissonClient.getReadWriteLock("rw-lock");
        readWriteLock.writeLock().lock();
        try {
            Thread.sleep(30000);
            redissonClient.getMapCache("name").put("name", s);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            readWriteLock.writeLock().unlock();
        }
        return s;
    }

    /**
     * 分布式读锁 --共享锁
     * @return
     */
    @GetMapping("/read")
    public String read() {
        String s = "";
        RReadWriteLock readWriteLock = redissonClient.getReadWriteLock("rw-lock");

        readWriteLock.readLock().lock();
        try {
            s = (String) redissonClient.getMapCache("name").get("name");
        } finally {
            readWriteLock.readLock().unlock();
        }
        return s;
    }

    /**
     * 分布式闭锁 -- 当到达设置的数量后才释放锁
     * @throws InterruptedException
     */
    @GetMapping("/countdown")
    public void countDownLock() throws InterruptedException {
        RCountDownLatch countDownLatch = redissonClient.getCountDownLatch("count-down-lock");
        countDownLatch.trySetCount(5);
        countDownLatch.await();
        log.info("完成");
    }

    /**
     * 分布式闭锁 -- 每次都减去一
     * @throws InterruptedException
     */
    @GetMapping("/go")
    public String go() {
        RCountDownLatch countDownLatch = redissonClient.getCountDownLatch("count-down-lock");
        countDownLatch.countDown();
        return "计数-1";
    }


    /**
     * 分布式信号量锁 -- 可以作限流的功能，首先预设多少个请求可以进来，当到达预设的值以后禁止其他的进来
     * @return
     */
    @GetMapping("park")
    public String park(){
        RSemaphore park = redissonClient.getSemaphore("park");
        boolean b = park.tryAcquire();
        if(b){
           return "有车位";
        }else{
            return "没车位了";
        }
    }


    /**
     * 分布式信号量锁 -- 可以作限流的功能，当一个请求完成我们就
     * @return
     */
    @GetMapping("/lease")
    public String leave(){
        redissonClient.getSemaphore("park").release();
        return "离开";
    }


}
