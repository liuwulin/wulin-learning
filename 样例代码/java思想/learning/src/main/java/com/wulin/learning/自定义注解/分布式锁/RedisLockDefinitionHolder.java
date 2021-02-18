package com.wulin.learning.自定义注解.分布式锁;

import lombok.Data;

/**
 * @author 武林
 * @date 2021/1/5 17:05
 */
@Data
public class RedisLockDefinitionHolder {

    /**
     * 业务唯一 key
     */
    private String businessKey;

    /**
     * 加锁时间 (秒 s)
     */
    private Long lockTime;

    /**
     * 上次更新时间（ms）
     */
    private Long lastModifyTime;

    /**
     * 保存当前线程
     */
    private Thread currentTread;

    /**
     * 总共尝试次数
     */
    private int tryCount;

    /**
     * 当前尝试次数
     */
    private int currentCount;

    /**
     * 更新的时间周期（毫秒）,公式 = 加锁时间（转成毫秒） / 3
     */
    private Long modifyPeriod;

    public RedisLockDefinitionHolder(String businessKey, Long lockTime, Long lastModifyTime, Thread currentTread, int tryCount) {
        this.businessKey = businessKey;
        this.lockTime = lockTime;
        this.lastModifyTime = lastModifyTime;
        this.currentTread = currentTread;
        this.tryCount = tryCount;
        this.modifyPeriod = lockTime * 1000 / 3;
    }
}