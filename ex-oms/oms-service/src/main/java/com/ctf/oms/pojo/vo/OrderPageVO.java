package com.ctf.oms.pojo.vo;

import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * 订单分页视图对象
 *
 * @author H.m
 * @date 2022/8/5 10:30
 */
@Data
public class OrderPageVO {

    private Long id;

    private String orderSn;

    private Long totalAmount;

    private Long payAmount;

    private Integer payType;

    private Integer status;

    private Integer totalQuantity;

    private Date createTime;

    private Long memberId;

    private Integer sourceType;

    private List<OrderItem> orderItems;

    @Data
    public static class OrderItem {

        private Long id;

        private Long orderId;

        private Long skuId;

        private String skuName;

        private String picUrl;

        private Long price;

        private Integer count;

        private Long totalAmount;

        private String spuName;

    }

}
