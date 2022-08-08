package com.ctf.oms.pojo.vo;

import lombok.Data;

/**
 * 订单创建响应视图对象
 *
 * @author H.m

 * @date 2022/8/5 10:30
 */
@Data
public class OrderSubmitVO {

    /**
     * 订单ID
     */
    private Long orderId;

    /**
     * 订单编号，进入支付页面显示
     */
    private String orderSn;

}
