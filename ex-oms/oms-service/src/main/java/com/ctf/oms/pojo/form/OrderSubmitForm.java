package com.ctf.oms.pojo.form;

import com.ctf.oms.pojo.dto.OrderItemDTO;
import com.ctf.ums.dto.MemberAddressDTO;
import lombok.Data;

import javax.validation.constraints.Size;
import java.util.List;

/**
 * 订单提交表单对象
 *
 * @author H.m

 * @date 2022/8/5 10:30
 */
@Data
public class OrderSubmitForm {

    /**
     * 提交订单确认页面签发的令牌
     */
    private String orderToken;

    /**
     * 订单总金额-用于验价(单位：分)
     */
    private Long totalAmount;

    /**
     * 支付金额(单位：分)
     */
    private Long payAmount;

    /**
     * 订单的商品明细
     */
    private List<OrderItemDTO> orderItems;

    /**
     * 订单备注
     */
    @Size(max = 500, message = "订单备注长度不能超过500")
    private String remark;

    /**
     * 优惠券ID
     */
    private String couponId;

    /**
     * 收获地址
     */
    private MemberAddressDTO deliveryAddress;

}
