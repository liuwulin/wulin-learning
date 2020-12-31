package com.example.jpa.redis;

import lombok.Data;
import lombok.ToString;

/**
 * @author Liu WuLin
 * @date 1101 11:29
 */
@Data
@ToString
public class RedisSentinelProperties {

    private String master;

    private String nodes;

    /**
     * 哨兵配置
     */
    private boolean masterOnlyWrite;

    /**
     *
     */
    private int failMax;
}
