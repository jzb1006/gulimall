package com.example.gulimall.product.config;

import org.springframework.boot.autoconfigure.cache.CacheProperties;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@EnableCaching
@Configuration
// 启用本地配置
@EnableConfigurationProperties(CacheProperties.class)
public class MyRedisConfig {

    @Bean
    RedisCacheConfiguration redisCacheConfiguration(CacheProperties cacheProperties) {
        RedisCacheConfiguration configuration = RedisCacheConfiguration.defaultCacheConfig();
        configuration = configuration.serializeKeysWith(RedisSerializationContext.SerializationPair.fromSerializer(new StringRedisSerializer()));
        configuration = configuration.serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(new GenericJackson2JsonRedisSerializer()));

        CacheProperties.Redis redis = cacheProperties.getRedis();

        if (redis.getTimeToLive() != null) {
            configuration = configuration.entryTtl(redis.getTimeToLive());
        }

        if (redis.getKeyPrefix() != null) {
            configuration = configuration.prefixCacheNameWith(redis.getKeyPrefix());
        }

        if (!redis.isCacheNullValues()) {
            configuration = configuration.disableCachingNullValues();
        }

        if (!redis.isUseKeyPrefix()) {
            configuration = configuration.disableKeyPrefix();
        }


        return configuration;
    }
}
