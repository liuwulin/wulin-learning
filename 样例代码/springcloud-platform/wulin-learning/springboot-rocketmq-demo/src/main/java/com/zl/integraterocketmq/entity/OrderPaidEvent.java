package com.zl.integraterocketmq.entity;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @Author: zhouliang
 * @Date: 2019/7/11 14:41
 */
@Data
public class OrderPaidEvent implements Serializable {

    private static final long serialVersionUID = -8983677932582480532L;

    private String orderId;

    private BigDecimal paidMoney;

    private Integer orderly;
}
