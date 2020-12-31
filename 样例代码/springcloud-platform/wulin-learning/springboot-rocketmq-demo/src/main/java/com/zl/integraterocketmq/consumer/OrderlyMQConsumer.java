package com.zl.integraterocketmq.consumer;

/**
 * @Author: zhouliang
 * @Date: 2019/7/11 18:42
 */

import com.zl.integraterocketmq.entity.OrderPaidEvent;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.annotation.ConsumeMode;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Service;

/**
 * @Author: zhouliang
 * @Date: 2019/7/11 14:52
 * @Desc: RocketMQ在设计时就不希望一个消费者同时处理多个类型的消息，
 * 因此同一个consumerGroup下的consumer职责应该是一样的，
 * 不要干不同的事情（即消费多个topic）。建议consumerGroup与topic一一对应。
 * <p>
 * 顺序消费消费者需要设置为:consumeMode : ConsumeMode.ORDERLY
 * <p>
 * 注意：消费者默认实现的接口为MessageListenerConcurrently， 对应为ConsumeMode.CONCURRENTLY
 * 消费者实现MessageListenerOrderly的时候，就是为顺序消费。
 */
@Slf4j
@Service
@RocketMQMessageListener(consumeMode = ConsumeMode.ORDERLY, topic = "orderly-topic", consumerGroup = "my-consumer_orderly-topic")
public class OrderlyMQConsumer implements RocketMQListener<OrderPaidEvent> {

    @Override
    public void onMessage(OrderPaidEvent message) {
        log.info("MQ顺序测试消费端接收到消息:{}", message.toString());
    }
}
