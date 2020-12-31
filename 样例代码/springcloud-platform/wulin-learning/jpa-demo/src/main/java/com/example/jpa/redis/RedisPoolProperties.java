package com.example.jpa.redis;

import lombok.Data;
import lombok.ToString;

/**
 * @author Liu WuLin
 * @date 1101 11:33
 */
@Data
@ToString
public class RedisPoolProperties {
    private int maxIdle;
    private int minIdle;
    private int maxActive;
    private int maxWait;
    private int connTimeout;

    private int soTimeout;
    private  int size;


}
