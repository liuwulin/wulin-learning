package com.zl.integraterocketmq.listener;

import com.zl.integraterocketmq.entity.OrderPaidEventTx;
import com.zl.integraterocketmq.util.JsonUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.remoting.common.RemotingHelper;
import org.apache.rocketmq.spring.annotation.RocketMQTransactionListener;
import org.apache.rocketmq.spring.core.RocketMQLocalTransactionListener;
import org.apache.rocketmq.spring.core.RocketMQLocalTransactionState;
import org.springframework.messaging.Message;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * @Author: zhouliang
 * @Date: 2019/7/11 20:13
 */
@Slf4j
@RocketMQTransactionListener(txProducerGroup = "test-txProducerGroup-name")
public class TransactionListenerImpl implements RocketMQLocalTransactionListener {

    /**
     * RocketMQLocalTransactionState说明：
     *
     * COMMIT：
     *         表示事务消息被提交，会被正确分发给消费者。(事务消息先会发送到broker，对消费端不可见，为UNKNOWN状态，在这里回调的时候如果返回COMMIT
     *         那么。消费端马上就会接收到消息。)
     *
     * ROLLBACK：
     *          该状态表示该事务消息被回滚，因为本地事务逻辑执行失败导致（如数据库异常，或SQL异常，或其他未知异常，这时候消息在broker会被删除掉，
     *          不会发送到consumer）
     * UNKNOWN：
     *        表示事务消息未确定，可能是业务方执行本地事务逻辑时间耗时过长或者网络原因等引起的，该状态会导致broker对事务消息进行回查，默认回查
     *        总次数是15次，第一次回查间隔时间是6s，后续每次间隔60s,（消息一旦发送状态就为中间状态：UNKNOWN）
     */


    /**
     * 执行本地的事务逻辑
     *
     * @param msg
     * @param arg
     * @return
     */
    @Override
    public RocketMQLocalTransactionState executeLocalTransaction(Message msg, Object arg) {
        log.info("执行本地事务逻辑：{}", msg);
        CountDownLatch latch = null;
        try {
            latch = (CountDownLatch) arg;
            byte[] body = (byte[]) msg.getPayload();
            String json = new String(body, RemotingHelper.DEFAULT_CHARSET);
            OrderPaidEventTx orderPaidEventTx = JsonUtil.jsonToObject(json, OrderPaidEventTx.class);

            //执行本地事务，比如下单成功，存储订单信息。
            assert orderPaidEventTx != null;
            log.info("执行本地事务接收到到的消息内容为:{}", orderPaidEventTx.toString());

            //模拟现实中的业务逻辑
            TimeUnit.SECONDS.sleep(2);

            //本地事务执行成功。
            return RocketMQLocalTransactionState.COMMIT;
        } catch (Exception e) {
            e.printStackTrace();
            log.error("本地事务执行失败：如数据库异常，或SQL异常，或其他未知异常，异常原因:{}", e.getMessage());
            return RocketMQLocalTransactionState.ROLLBACK;
        } finally {
            if (null != latch) {
                //事务提交完成，或代码执行完成一定要把CountDownLatch 归0，不然会造成主线程阻塞。
                latch.countDown();
            }
        }
    }

    /**
     * 执行事务回查逻辑
     *
     * @param msg
     * @return
     */
    @Override
    public RocketMQLocalTransactionState checkLocalTransaction(Message msg) {
        //6s 本地事务还没执行完成，就会触发回调检查的方法。

        //这时候就要做自己对这个订单的异常处理，最后根据处理的情况来决定，
        // 重新发送这个订单到消费者 还是删除还是继续回查。

//     如：   return RocketMQLocalTransactionState.COMMIT; 重新发送到消费端
//        return RocketMQLocalTransactionState.ROLLBACK;  消费在broker删除，不会发送到消费端
        return null;
    }
}
