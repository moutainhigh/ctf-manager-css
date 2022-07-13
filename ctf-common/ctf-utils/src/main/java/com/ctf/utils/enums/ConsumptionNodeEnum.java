package com.ctf.utils.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * consumption节点枚举
 * @author yewei
 * @date 2022-05-31
 */
@Getter
@AllArgsConstructor
public enum ConsumptionNodeEnum {

    EXCEPTION("Exception","Exception"),
    ORDER_CREATION("order_creation","order_creation"),
    ORDER_RELEASE("order_release","order_release"),
    POD("POD","POD"),
    ;
    private String code;
    private String name;
}
