package com.ctf.oms.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author H.m
 * @desc 订单来源类型枚举

 * @date 2022/8/5 10:30
 */

@AllArgsConstructor
public enum OrderTypeEnum  {

    WEB(0), // PC订单
    APP(1), // APP订单
    ;

    @Getter
    private Integer code;

    public static OrderTypeEnum getValue(Integer code){
        for (OrderTypeEnum value : values()) {
            if (value.getCode().equals(code)) {
                return value;
            }
        }
        return null;
    }
}
