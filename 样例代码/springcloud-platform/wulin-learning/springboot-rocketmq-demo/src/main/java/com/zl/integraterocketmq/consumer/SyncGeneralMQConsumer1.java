package com.zl.integraterocketmq.consumer;

import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Service;

/**
 * @Author: zhouliang
 * @Date: 2019/7/11 14:52
 * @Desc: RocketMQ在设计时就不希望一个消费者同时处理多个类型的消息，
 * 因此同一个consumerGroup下的consumer职责应该是一样的，
 * 不要干不同的事情（即消费多个topic）。建议consumerGroup与topic一一对应。
 *
 * https://github.com/apache/rocketmq-spring/blob/master/README_zh_CN.md FAQ:3
 */
@Slf4j
@Service
@RocketMQMessageListener(topic = "string-topic", consumerGroup = "my-consumer_string-topic")
public class SyncGeneralMQConsumer1 implements RocketMQListener<String> {

    @Override
    public void onMessage(String message) {
        log.info("SyncGeneralMQConsumer1端接收到消息:{}", message);
    }
}
