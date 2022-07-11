package com.example.gulimall.product.redis;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Test;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.util.concurrent.TimeUnit;

@Slf4j
@SpringBootTest
public class RedisTest {
    @Autowired
    StringRedisTemplate stringRedisTemplate;

    @Autowired
    RedissonClient redissonClient;

    /**
     * todo 本地锁测试，在分布式的情况下，必须使用分布式锁。
     * 1、防止缓存穿透的解决办法就是查询到的结果就算是为空的也要写入缓存中，这样就不会穿透了。但是过期时间可以设置的短一点，比如1分钟。
     * 2、防止缓存击穿的方法就是加入锁机制，防止热点数据刚好过期的同时大量的数据击穿缓存直接进入数据库。
     * 3、缓存雪崩的就是所有的数据在同一时间过期了，导致大部分请求直接到数据库，解决办法就是设置不同的过期时间。
     */
    @Test
    public void test() {
        synchronized (this) {
            String name = stringRedisTemplate.opsForValue().get("name");
            if (name == null){
                name = "";
            }
            if (StringUtils.isEmpty(name)){
                name = stringRedisTemplate.opsForValue().get("name");
                if (!StringUtils.isEmpty(name)){
                    log.info(name);
                    return;
                }
                log.info("查询了数据库");
                stringRedisTemplate.opsForValue().set("name", "zhangsan", 60, TimeUnit.SECONDS);
            }
            log.info(name);
        }
    }

    @Test
    public void redisson(){
        redissonClient.getMapCache("name").put("name", "zhangsan111", 60, TimeUnit.SECONDS);
        log.info((String) redissonClient.getMapCache("name").get("name"));
    }
}
