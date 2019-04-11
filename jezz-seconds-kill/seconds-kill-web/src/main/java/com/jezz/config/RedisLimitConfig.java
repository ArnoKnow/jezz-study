package com.jezz.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;

@Configuration
public class RedisLimitConfig {

    private Logger logger = LoggerFactory.getLogger(RedisLimitConfig.class);

    @Value("${redis.ratelimit}")
    private int limit;


    @Autowired
    private JedisConnectionFactory jedisConnectionFactory;

//    @Bean
//    public RedisLimit build() {
//        RedisLimit redisLimit = new RedisLimit.Builder(jedisConnectionFactory, RedisToolsConstant.SINGLE)
//                .ratelimit(ratelimit)
//                .build();
//
//        return redisLimit;
//    }
}
