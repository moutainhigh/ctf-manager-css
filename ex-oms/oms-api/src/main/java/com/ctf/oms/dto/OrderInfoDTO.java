package com.ctf.oms.dto;

import lombok.Data;

/**
 * 订单信息传输层对象
 *
 * @author H.m
 * @date 2022/8/5 10:30
 */
@Data
public class OrderInfoDTO {

    /**
     * 订单编号
     */
    private String orderSn;

    /**
     * 订单状态
     */
    private Integer status;

}
