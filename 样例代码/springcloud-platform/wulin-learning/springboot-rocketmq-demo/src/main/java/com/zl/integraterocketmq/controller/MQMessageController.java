package com.zl.integraterocketmq.controller;

import com.zl.integraterocketmq.service.GeneralMQMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: zhouliang
 * @Date: 2019/7/11 14:12
 * @Desc: 对 RocketMQTemplate 封装原始rocketMq 一些常用方法的示例和测试。
 * 结合官方源码和README_zh_CN.md 看。
 * rocketmq-spring-boot-starter 源码地址：https://github.com/apache/rocketmq-spring
 */
@RestController
@RequestMapping("/rocketMq")
public class MQMessageController {

    @Autowired
    private GeneralMQMessageService generalMQMessageService;

    /**
     * rocketMq同步消息发送测试
     */
    @RequestMapping("/syncGeneralMQMessageSend")
    public void syncGeneralMQMessageSend() {
        generalMQMessageService.syncGeneralMQMessageSend();
    }

    /**
     * rocketMq异步消息发送测试
     */
    @RequestMapping("/asyncSendMQMessageSend")
    public void asyncSendMQMessageSend() {
        generalMQMessageService.asyncSendMQMessageSend();
    }

    /**
     * rocketMq单向发送不关心结果的发送测试【日志收集】
     */
    @RequestMapping("/oneWaySendMQMessageSend")
    public void oneWaySendMQMessageSend() {
        generalMQMessageService.oneWaySendMQMessageSend();
    }

    /**
     * 延迟消息发送测试
     */
    @RequestMapping("/delayedSendMQMessageSend")
    public void delayedSendMQMessageSend() {
        generalMQMessageService.delayedSendMQMessageSend();
    }

    /**
     * 顺序消息发送
     */
    @RequestMapping("/orderlyMQMessageSend")
    public void orderlyMQMessageSend() {
        generalMQMessageService.orderlyMQMessageSend();
    }

    /**
     * 分布式事务消息发送
     */
    @RequestMapping("/transactionMQSend")
    public void transactionMQSend() {
        generalMQMessageService.transactionMQSend();
    }

    /**
     * 消息过滤消息发送
     */
    @RequestMapping("/selectorMQSend")
    public void selectorMQSend() {
        generalMQMessageService.selectorMQSend();
    }
}
