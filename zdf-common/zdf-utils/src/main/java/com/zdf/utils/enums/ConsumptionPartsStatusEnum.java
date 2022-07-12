package com.zdf.utils.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * consumption物料状态枚举
 * @author yewei
 * @date 2022-05-31
 */
@Getter
@AllArgsConstructor
public enum ConsumptionPartsStatusEnum {


    WWH_INBOUND("WWH inbound","WWH inbound"),
    DC_INBOUND("DC inbound","DC inbound"),
    WWH_OUTBOUND("WWH outbound","WWH outbound"),
    DC_OUTBOUND("DC outbound","DC outbound"),
    CONCUMPTION_IN_TRANSIT("Consumption in transit","Consumption in transit"),
    REPLENISHMENT_IN_TRANSTI("Replenishment in transit","Replenishment in transit"),
    ;
    private String code;
    private String name;
}
