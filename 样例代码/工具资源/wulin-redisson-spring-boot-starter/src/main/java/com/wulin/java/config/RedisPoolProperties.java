package com.wulin.java.config;

import lombok.Data;
import lombok.ToString;

/**
 * @author 武林
 * @date 2021/1/4 18:08
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

    /**
     * 池大小
     */
    private  int size;

}
