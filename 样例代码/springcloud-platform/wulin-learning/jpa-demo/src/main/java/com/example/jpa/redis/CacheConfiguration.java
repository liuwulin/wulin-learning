package com.example.jpa.redis;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.redisson.config.ReadMode;
import org.redisson.config.SentinelServersConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Liu WuLin
 * @date 1101 11:26
 */

@Configuration
@EnableConfigurationProperties(RedisProperties.class)
public class CacheConfiguration {

    @Autowired
    RedisProperties redisProperties;

    @Configuration
    @ConditionalOnClass({Redisson.class})
    protected class RedissonSingleClientConfiguration {

        @Bean
        RedissonClient redissonSentinel() {
            System.out.println("sentinel redisProperties:" + redisProperties.getSentinel());
            Config config = new Config();
//            String[] nodes = redisProperties.getSentinel().getNodes().split(",");
//            List<String> newNodes = new ArrayList(nodes.length);
//            Arrays.stream(nodes).forEach((index) -> newNodes.add(
//                    index.startsWith("redis://") ? index : "redis://" + index));
            SentinelServersConfig serverConfig = config.useSentinelServers()
                    .addSentinelAddress("redis://120.24.72.198:26379")
                    .addSentinelAddress("redis://120.24.72.198:26380")
                    .addSentinelAddress("redis://120.24.72.198:26381")
                    .setMasterName("mymaster")
                    .setReadMode(ReadMode.SLAVE)

                    .setTimeout(3000);


                serverConfig.setPassword("test@dbuser2018");


            return Redisson.create(config);
        }
    }
}