package com.example.gulimall.product.app;

import lombok.extern.slf4j.Slf4j;
import org.redisson.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
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
     *
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
     *
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
     *
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
     *
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
     *
     * @return
     */
    @GetMapping("park")
    public String park() {
        RSemaphore park = redissonClient.getSemaphore("park");
        boolean b = park.tryAcquire();
        if (b) {
            return "有车位";
        } else {
            return "没车位了";
        }
    }


    /**
     * 分布式信号量锁 -- 可以作限流的功能，当一个请求完成我们就
     *
     * @return
     */
    @GetMapping("/lease")
    public String leave() {
        redissonClient.getSemaphore("park").release();
        return "离开";
    }

    @GetMapping("/cache1")
    @Cacheable(value = "category", key = "#root.methodName", sync = true)
    public String cacheCategory() {
        return "{\n" +
                "\t\"deleted_user_attributes\": [],\n" +
                "\t\"system_notifications\": [],\n" +
                "\t\"timestamp_unixtime_ms\": 1657129807000,\n" +
                "\t\"batch_id\": -4730452683874612880,\n" +
                "\t\"user_attributes\": {\n" +
                "\t\t\"Google offline conversion - 1 for ordercount\": \"0\",\n" +
                "\t\t\"registration_product\": \"trading\",\n" +
                "\t\t\"acquisition_medium\": \"cpc\",\n" +
                "\t\t\"symbol_interest\": [\"EURUSD\", \"GBPUSD\", \"USDCAD\"],\n" +
                "\t\t\"reserved.mobile\": \"+2347064971413\",\n" +
                "\t\t\"live_order_count\": \"3533\",\n" +
                "\t\t\"manager_order_count\": \"0\",\n" +
                "\t\t\"last_deposit_success\": \"2022-06-30T10:28:59.0000000Z\",\n" +
                "\t\t\"first_deposit_success\": \"2020-07-14T14:20:43.0000000Z\",\n" +
                "\t\t\"gtm_cid\": \"True\",\n" +
                "\t\t\"product_account_types_list\": \"Advantage MT4 Live,FXTM Standard Live,Advantage Plus MT4 Live\",\n" +
                "\t\t\"reserved.lastname\": \"Chukwuemeke\",\n" +
                "\t\t\"reserved.firstname\": \"Precious\",\n" +
                "\t\t\"registration_country\": \"NG\",\n" +
                "\t\t\"symbols_30d\": [\"GBPUSD\", \"USDCAD\"],\n" +
                "\t\t\"deposit_total_usd\": \"4271.31\",\n" +
                "\t\t\"acquisition_channel\": \"PPC-GOOGLE\",\n" +
                "\t\t\"demo_order_count\": \"0\",\n" +
                "\t\t\"first_manager_account_open\": \"2022-01-15T10:19:40.0000000Z\",\n" +
                "\t\t\"loyalty_flag\": \"False\",\n" +
                "\t\t\"reserved.gender\": \"M\",\n" +
                "\t\t\"client_status\": \"Approved\",\n" +
                "\t\t\"deposit_count\": \"222\",\n" +
                "\t\t\"registration_language\": \"en\",\n" +
                "\t\t\"first_live_order_placed\": \"2021-08-03T11:00:42.0000000Z\",\n" +
                "\t\t\"last_live_order_placed\": \"2022-07-01T18:56:46.0000000Z\",\n" +
                "\t\t\"dob\": \"1995-07-18\",\n" +
                "\t\t\"manager_account_count\": \"1\"\n" +
                "\t},\n" +
                "\t\"message_id\": \"2108285f-570f-4799-ab36-2ddaac55b025\",\n" +
                "\t\"message_type\": \"events\",\n" +
                "\t\"mpid\": -7310364609873237976,\n" +
                "\t\"mp_deviceid\": \"00000000-0000-4000-8000-0000000027bb\",\n" +
                "\t\"schema_version\": 1,\n" +
                "\t\"environment\": \"production\",\n" +
                "\t\"user_identities\": [{\n" +
                "\t\t\"timestamp_unixtime_ms\": 1608563615396,\n" +
                "\t\t\"identity\": \"62307995\",\n" +
                "\t\t\"identity_type\": \"customer_id\"\n" +
                "\t}, {\n" +
                "\t\t\"timestamp_unixtime_ms\": 1608563615396,\n" +
                "\t\t\"identity\": \"prezinosly@yahoo.com\",\n" +
                "\t\t\"identity_type\": \"email\"\n" +
                "\t}, {\n" +
                "\t\t\"timestamp_unixtime_ms\": 1613832635037,\n" +
                "\t\t\"identity\": \"62307995\",\n" +
                "\t\t\"identity_type\": \"other\"\n" +
                "\t}, {\n" +
                "\t\t\"timestamp_unixtime_ms\": 1654102180129,\n" +
                "\t\t\"identity\": \"1178370979.1650962947\",\n" +
                "\t\t\"identity_type\": \"other_id_4\"\n" +
                "\t}],\n" +
                "\t\"context\": {},\n" +
                "\t\"events\": [{\n" +
                "\t\t\"data\": {\n" +
                "\t\t\t\"timestamp_unixtime_ms\": \"1657129807000\",\n" +
                "\t\t\t\"custom_event_type\": \"other\",\n" +
                "\t\t\t\"custom_attributes\": {\n" +
                "\t\t\t\t\"payment_system_dst\": \"Trade Account\",\n" +
                "\t\t\t\t\"account_type\": \"FXTM Standard\",\n" +
                "\t\t\t\t\"amount\": \"3900\",\n" +
                "\t\t\t\t\"is_first_event\": \"False\",\n" +
                "\t\t\t\t\"app_type\": \"undefined\",\n" +
                "\t\t\t\t\"trading_platform\": \"MetaTrader4\",\n" +
                "\t\t\t\t\"amount_usd\": \"6.78\",\n" +
                "\t\t\t\t\"account_login\": \"120118757\",\n" +
                "\t\t\t\t\"account_type_id\": \"1\",\n" +
                "\t\t\t\t\"transfer_id\": \"9762375\",\n" +
                "\t\t\t\t\"trading_server\": \"ForexTimeFXTM-Standard\",\n" +
                "\t\t\t\t\"platform_type\": \"undefined\",\n" +
                "\t\t\t\t\"transfer_status_name\": \"Rejected\",\n" +
                "\t\t\t\t\"is_account_activated\": \"True\",\n" +
                "\t\t\t\t\"transfer_type_id\": \"1\",\n" +
                "\t\t\t\t\"transfer_status_id\": \"4\",\n" +
                "\t\t\t\t\"currency\": \"NGN\",\n" +
                "\t\t\t\t\"is_mobile_app\": \"False\",\n" +
                "\t\t\t\t\"amount_usd_cents\": \"678\",\n" +
                "\t\t\t\t\"payment_system_id\": \"87\",\n" +
                "\t\t\t\t\"payment_system_name\": \"Guaranty Trust Bank\",\n" +
                "\t\t\t\t\"mParticle Source Feed\": \"Internal CDP\",\n" +
                "\t\t\t\t\"transfer_type_name\": \"Deposit\"\n" +
                "\t\t\t},\n" +
                "\t\t\t\"event_id\": \"1463933401026019621\",\n" +
                "\t\t\t\"custom_flags\": {},\n" +
                "\t\t\t\"event_name\": \"deposit_failed\"\n" +
                "\t\t},\n" +
                "\t\t\"event_type\": \"custom_event\"\n" +
                "\t}]\n" +
                "}";
    }

    @GetMapping("cache2")
    @Cacheable(value = "category", key = "#root.methodName", sync = true)
    public String cacheCategory2() {
        return "{\"cacheCategory2\":\"cacheCategory2}";
    }

    @GetMapping("update-cate")
    @CacheEvict(value = "category", allEntries = true) // 失效模式
    @CachePut(value = "category", key = "'cacheCategory2'") // 双写模式
    public String updateCateCategory() {
        return "success";
    }

}
