package com.nefedov.nexigntestapplication.configuration;

import com.nefedov.nexigntestapplication.utils.StringPool;
import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RedissonConfiguration {

    private final static String REDIS = "redis://";

    @Value("${spring.data.redis.host}")
    private String redisHost;

    @Value("${spring.data.redis.port}")
    private String redisPort;

    @Bean
    public RedissonClient redissonClient() {
        Config config = new Config();
        config.useSingleServer().setAddress(REDIS + redisHost + StringPool.COLON + redisPort);
        return Redisson.create(config);
    }
}
