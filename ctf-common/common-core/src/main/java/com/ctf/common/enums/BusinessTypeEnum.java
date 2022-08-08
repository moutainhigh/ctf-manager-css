package com.ctf.common.enums;

import com.ctf.common.base.IBaseEnum;
import lombok.Getter;

/**
 * 业务类型枚举
 *
 * @author H.m
 * @date 2022/8/5 10:30
 */
public enum BusinessTypeEnum implements IBaseEnum<Integer> {

    USER(100, "用户"),
    MEMBER(200, "会员"),
    ORDER(300, "订单");

    @Getter
    private Integer value;

    @Getter
    private String label;

    BusinessTypeEnum(Integer value, String label) {
        this.value = value;
        this.label = label;
    }
}
