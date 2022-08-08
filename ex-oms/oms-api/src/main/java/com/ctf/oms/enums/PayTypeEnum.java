package com.ctf.oms.enums;


import com.ctf.common.base.IBaseEnum;
import lombok.Getter;

/**
 * @author H.m
 * @desc 订单支付类型枚举

 * @date 2022/8/5 10:30
 */
public enum PayTypeEnum implements IBaseEnum<Integer> {

    WX_JSAPI(1, "微信JSAPI支付"),
    ALIPAY(2, "支付宝支付"),
    BALANCE(3, "会员余额支付"),
    WX_APP(4, "微信APP支付");

    PayTypeEnum(int value, String label) {
        this.value = value;
        this.label = label;
    }

    @Getter
    private Integer value;

    @Getter
    private String label;
}
