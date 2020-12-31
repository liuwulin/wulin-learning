package com.zl.integraterocketmq.consumer;

import com.zl.integraterocketmq.entity.OrderPaidEventTx;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.annotation.ConsumeMode;
import org.apache.rocketmq.spring.annotation.MessageModel;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Service;

/**
 * @Author: zhouliang
 * @Date: 2019/7/12 10:48
 */
@Slf4j
@Service
@RocketMQMessageListener(consumeMode = ConsumeMode.CONCURRENTLY, topic = "tx-text-topic",
        consumerGroup = "my-consumer_tx-text-topic", messageModel = MessageModel.CLUSTERING)
public class TxMQConsumer implements RocketMQListener<OrderPaidEventTx> {

    @Override
    public void onMessage(OrderPaidEventTx message) {
        log.info("消费端接收到事务消息：{}", message.toString());
    }
}
