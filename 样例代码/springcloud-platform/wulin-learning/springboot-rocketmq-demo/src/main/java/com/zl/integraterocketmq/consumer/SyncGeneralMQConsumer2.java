package com.zl.integraterocketmq.consumer;

import com.zl.integraterocketmq.entity.OrderPaidEvent;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Service;

/**
 * @Author: zhouliang
 * @Date: 2019/7/11 14:57
 * @Desc: RocketMQ在设计时就不希望一个消费者同时处理多个类型的消息，
 * 因此同一个consumerGroup下的consumer职责应该是一样的，
 * 不要干不同的事情（即消费多个topic）。建议consumerGroup与topic一一对应。
 * https://github.com/apache/rocketmq-spring/blob/master/README_zh_CN.md FAQ:3
 */
@Slf4j
@Service
@RocketMQMessageListener(topic = "object-topic", consumerGroup = "my-consumer_object-topic")
public class SyncGeneralMQConsumer2 implements RocketMQListener<OrderPaidEvent> {

    @Override
    public void onMessage(OrderPaidEvent message) {
        log.info("SyncGeneralMQConsumer2 消费端接收到消息:{}", message.toString());
    }
}
