package com.example.jpa.redis;

import lombok.Data;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author Liu WuLin
 * @date 1101 11:31
 */
@ConfigurationProperties(prefix = "spring.redis", ignoreUnknownFields = false)
@Data
@ToString
public class RedisProperties {

    private int database;

    private int timeout;

    private String password;

    private String mode;

    /**
     * 池配置
     */
    private RedisPoolProperties pool;

//    private RedisSingleProperties single;
//
//    private RedisClusterProperties cluster;

    private RedisSentinelProperties sentinel;


}
