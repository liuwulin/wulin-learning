package com.zl.integraterocketmq.service.impl;

import com.zl.integraterocketmq.constant.MessageDelayLevel;
import com.zl.integraterocketmq.entity.OrderPaidEvent;
import com.zl.integraterocketmq.entity.OrderPaidEventTx;
import com.zl.integraterocketmq.service.GeneralMQMessageService;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.producer.*;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Random;
import java.util.concurrent.CountDownLatch;

/**
 * @Author: zhouliang
 * @Date: 2019/7/11 13:37
 */
@Slf4j
@Service
public class GeneralMQMessageServiceImpl implements GeneralMQMessageService {

    @Autowired
    private RocketMQTemplate rocketMQTemplate;

    /**
     * 同步发送的方式:
     * <p>
     * 指消息发送方发出数据后，会在收到接收方发回响应之后才发下一个数据包的通讯方式。
     */
    @Override
    public void syncGeneralMQMessageSend() {

        //发送string类型的消息 : SendResult syncSend(String destination, Object payload);
        //对应消费者：SyncGeneralMQConsumer1
        String stringTopic = "string-topic";
        String payloadStr = "Hello world!";
        SendResult sendResultStr = rocketMQTemplate.syncSend(stringTopic, payloadStr);
        log.info("MQ同步发送String类型的消息topic为:{},返回结果:{}", stringTopic, sendResultStr);

        //发送对象类型的消息 :  SendResult syncSend(String destination, Message<?> message);
        //对应消费者：SyncGeneralMQConsumer2
        String objectTopic = "object-topic";
        OrderPaidEvent orderPaidEvent = new OrderPaidEvent();
        orderPaidEvent.setOrderId("123456789");
        orderPaidEvent.setPaidMoney(new BigDecimal(20));
        Message<OrderPaidEvent> message = MessageBuilder.withPayload(orderPaidEvent).build();
        SendResult sendResultObj = rocketMQTemplate.syncSend(objectTopic, message);
        log.info("MQ同步发送对象类型（对象放到MessageBuilder构建）的消息topic为:{},返回结果:{}", stringTopic, sendResultObj);

        //发送对象类型的消息： SendResult syncSend(String destination, Object payload); convertAndSend 也可以发送消息，但是返回值为void
        //对应消费者：SyncGeneralMQConsumer2
        OrderPaidEvent orderPaidEvent1 = new OrderPaidEvent();
        orderPaidEvent1.setOrderId("987654321");
        orderPaidEvent1.setPaidMoney(new BigDecimal(50));
        SendResult sendResultObj1 = rocketMQTemplate.syncSend(objectTopic, orderPaidEvent1);
        log.info("MQ同步发送对象类型（直接传入对象）消息topic:{},返回结果:{}", stringTopic, sendResultObj1);

    }

    /**
     * 异步发送:
     * 指发送方发出数据后，不等接收方发回响应，接着发送下个数据包的通讯方式。
     * MQ 的异步发送，需要用户实现异步发送回调接口（SendCallback），在执行消息的异步发送时，
     * 应用不需要等待服务器响应即可直接返回，通过回调接口接收务器响应，并对服务器的响应结果进行处理。
     */
    @Override
    public void asyncSendMQMessageSend() {

        String objectTopic = "object-topic";
        OrderPaidEvent orderPaidEvent = new OrderPaidEvent();
        orderPaidEvent.setOrderId("123456789");
        orderPaidEvent.setPaidMoney(new BigDecimal(20));
        rocketMQTemplate.asyncSend(objectTopic, orderPaidEvent, new SendCallback() {
            @Override
            public void onSuccess(SendResult sendResult) {
                log.info("异步消息发送成功:{}", sendResult);
            }

            @Override
            public void onException(Throwable throwable) {
                log.error("异步消息发送失败:{}", throwable.getCause());
            }
        });
    }

    /**
     * 特点为只负责发送消息，不等待服务器回应且没有回调函数触发，即只发送请求不等待应答。
     * <p>
     * 此方式发送消息的过程耗时非常短，一般在微秒级别。
     * <p>
     * 应用场景：适用于某些耗时非常短，但对可靠性要求并不高的场景，例如日志收集。
     * <p>
     * 与异步发送的区别在于:异步其实还是要发送结果，不过是回调回来，不阻塞当前线程等待结果。
     */
    @Override
    public void oneWaySendMQMessageSend() {

        String objectTopic = "object-topic";
        OrderPaidEvent orderPaidEvent = new OrderPaidEvent();
        orderPaidEvent.setOrderId("123456789");
        orderPaidEvent.setPaidMoney(new BigDecimal(20));
        rocketMQTemplate.sendOneWay(objectTopic, orderPaidEvent);

    }

    /**
     * 延迟消息：
     * rocketMQ的延迟消息发送其实是已发送就已经到broker端了，然后消费端会延迟收到消息。
     * <p>
     * RocketMQ 目前只支持固定精度的定时消息。
     * 延迟级别（18个等级）
     * 1到18分别对应1s 5s 10s 30s 1m 2m 3m 4m 5m 6m 7m 8m 9m 10m 20m 30m 1h 2h
     * <p>
     * 为什么不支持固定精度的定时消息？
     * 因为rocketMQ之所以性能好的原因是broker端做的事情很少，基本都交给业务端做，就是消费端。如果支持
     * 我们自定义的延迟时候，会很大程度消耗broker的性能。
     * <p>
     * 延迟的底层方法是用定时任务实现的。
     */
    @Override
    public void delayedSendMQMessageSend() {
        //SendResult syncSend(String destination, Message<?> message, long timeout, int delayLevel);
        String objectTopic = "object-topic";
        OrderPaidEvent orderPaidEvent = new OrderPaidEvent();
        orderPaidEvent.setOrderId("123456789");
        orderPaidEvent.setPaidMoney(new BigDecimal(20));
        Message<OrderPaidEvent> message = MessageBuilder.withPayload(orderPaidEvent).build();
        //这里的2就是对应上面延迟5s的。就是延迟5s发送消息。
        SendResult sendResult = rocketMQTemplate.syncSend(objectTopic, message, 1000, MessageDelayLevel.TIME_5S);
        log.info("发送延迟消息返回结果:{}", sendResult);
    }

    @Override
    public void orderlyMQMessageSend() {

        //public SendResult syncSendOrderly(String destination, Object payload, String hashKey);
        String orderlyTopic = "orderly-topic";

        //同步顺序发送 - 对应顺序消费：
        for (int i = 0; i < 5; i++) {

            OrderPaidEvent orderPaidEvent = new OrderPaidEvent();
            String orderId = "123456";
            orderPaidEvent.setOrderId(orderId);
            orderPaidEvent.setPaidMoney(new BigDecimal(20));
            orderPaidEvent.setOrderly(i);

            // 源码导读：
            // * @param hashKey    use this key to select queue. for example: orderId, productId ...
            // 翻译过来为： 使用这个hashKey去选择投递到哪个队列，比如可以设置为:orderId 或则 productId
            // FAQ：rocketMQ创建topic的时候默认的队列长度为16个，那么这个hashKey，怎么通过自己设置的orderId或则producId变成队列标示的比如如果16和队列这个值应该在1-16之间？
            // 继续看源码：
            // List<MessageQueue> messageQueueList = mQClientFactory.getMQAdminImpl().parsePublishMessageQueues(topicPublishInfo.getMessageQueueList());
            // 上面这个方法就能获取这个topic创建的时候的队列长度。然后根据它底层有个取模的方法 取到其中一个队列
            // 取模的算法很简单 ：org.apache.rocketmq.client.producer.selector.SelectMessageQueueByHash.select
            // 所以为什么这个方法的源码说我们的hashkey可以设置为：orderId和productId,这样同样的orderId就可以进入同一个队列 按照顺序消费
            //EX:顺序消费的底层源码实现就是必须选择一个队列，然后在这个对面里面的消息和生产顺序保持一致。

            SendResult sendResult = rocketMQTemplate.syncSendOrderly(orderlyTopic, orderPaidEvent, orderId);
            log.info("同步发送顺序消息返回结果:{}", sendResult);
        }

        //异步顺序发送 - 对应顺序消费：
        for (int i = 0; i < 5; i++) {

            OrderPaidEvent orderPaidEvent = new OrderPaidEvent();
            String orderId = "654321";
            orderPaidEvent.setOrderId(orderId);
            orderPaidEvent.setPaidMoney(new BigDecimal(20));
            orderPaidEvent.setOrderly(i);

            rocketMQTemplate.asyncSendOrderly(orderlyTopic, orderPaidEvent, orderId, new SendCallback() {
                @Override
                public void onSuccess(SendResult sendResult) {
                    log.info("异步发送顺序返回结果为:{}", sendResult);
                }

                @Override
                public void onException(Throwable e) {
                    log.error("异步发送顺序消息失败，原因为:{}", e.getCause());
                }
            });

        }
    }

    /**
     * 分布式事务消息发送
     */
    @Override
    public void transactionMQSend() {

        //消息发送回调：com.zl.integraterocketmq.listener.TransactionListenerImpl
        String txProducerGroup = "test-txProducerGroup-name";
        //topic:tag
        String destination = "tx-text-topic";

        //同步阻塞
        CountDownLatch latch = new CountDownLatch(1);
        OrderPaidEventTx orderPaidEventTx = new OrderPaidEventTx();
        orderPaidEventTx.setOrderId("123456");
        orderPaidEventTx.setPaidMoney(new BigDecimal(20));

        Message<OrderPaidEventTx> message = MessageBuilder.withPayload(orderPaidEventTx).build();
//      sendMessageInTransaction(final String txProducerGroup, final String destination, final Message<?> message, final Object arg)
        TransactionSendResult sendResult = rocketMQTemplate.sendMessageInTransaction(txProducerGroup, destination, message, latch);

        if (sendResult.getSendStatus().equals(SendStatus.SEND_OK)
                && sendResult.getLocalTransactionState().equals(LocalTransactionState.COMMIT_MESSAGE)) {
            //下单成功，并且消息对消费端可见。

            //在这里可以异步通知上游服务，也可以继续走自己的逻辑，比如有些逻辑必须保证下单和库存成功之后才能走的。

            log.info("消息发送成功，并且本地事务执行成功");
        }
        try {
            latch.await();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void selectorMQSend() {
        for (int i = 0; i < 5; i++) {
            Random random = new Random();
            int k = random.nextInt(3) % 2;
            OrderPaidEvent orderPaidEvent = new OrderPaidEvent();
            orderPaidEvent.setOrderly(k);
            orderPaidEvent.setPaidMoney(new BigDecimal(0));
            orderPaidEvent.setOrderId("123456");

            String selectorTopic = "selector_topic";
            String tag = k == 0 ? ":tag1" : ":tag2";
            SendResult sendResult = rocketMQTemplate.syncSend(selectorTopic + tag, orderPaidEvent);
            log.info("消息过滤发送成功返回结果为:{}，当前k的参数值为:{}", sendResult, k);
        }
    }
}
