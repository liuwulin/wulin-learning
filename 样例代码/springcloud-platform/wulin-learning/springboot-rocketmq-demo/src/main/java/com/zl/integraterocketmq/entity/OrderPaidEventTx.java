package com.zl.integraterocketmq.entity;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @Author: zhouliang
 * @Date: 2019/7/11 20:10
 */
@Data
public class OrderPaidEventTx implements Serializable {

    private static final long serialVersionUID = 6785405514983538778L;

    private String orderId;

    private BigDecimal paidMoney;
}
