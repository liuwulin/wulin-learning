package com.wulin.java.config;

import lombok.Data;
import lombok.ToString;

/**
 * @author 武林
 * @date 2021/1/4 18:09
 */
@Data
@ToString
public class RedisSentinelProperties {

    /**
     * 哨兵master 名称
     */
    private String master;

    /**
     * 哨兵节点
     */
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
