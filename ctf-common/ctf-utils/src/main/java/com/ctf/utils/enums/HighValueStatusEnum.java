package com.ctf.utils.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * HighValue查询页面展示状态枚举
 * @author yewei
 * @date 2022-05-31
 */
@Getter
@AllArgsConstructor
public enum HighValueStatusEnum {
    WWH_IN_STOCK("WWH in Stock","WWH in Stock"),
    REPLE_IN_TRANSIT("Replenishment in transit","Replenishment in transit"),
    DC_IN_STOCK("DC in Stock","DC in Stock"),
    CONSUMP_IN_TRANSIT("Consumption in transit","Consumption in transit"),

    ;
    private String code;
    private String name;
}
