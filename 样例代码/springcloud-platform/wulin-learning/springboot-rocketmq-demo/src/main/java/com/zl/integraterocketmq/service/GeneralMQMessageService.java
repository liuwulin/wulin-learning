package com.zl.integraterocketmq.service;

/**
 * @Author: zhouliang
 * @Date: 2019/7/11 13:37
 * @Desc rocketMQ 普通消息发送示例
 */
public interface GeneralMQMessageService {
    /**
     * rocketMQ发送同步消息测试
     */
    void syncGeneralMQMessageSend();

    /**
     * rocketMQ异步消息发送测试
     */
    void asyncSendMQMessageSend();

    /**
     * rocketMq单向发送测试
     */
    void oneWaySendMQMessageSend();

    /**
     * 延迟消息测试
     */
    void delayedSendMQMessageSend();

    /**
     * 顺序消息发送
     */
    void orderlyMQMessageSend();

    /**
     * 分布式事务消息发送测试
     */
    void transactionMQSend();

    /**
     * 消息过滤发送
     */
    void selectorMQSend();
}
