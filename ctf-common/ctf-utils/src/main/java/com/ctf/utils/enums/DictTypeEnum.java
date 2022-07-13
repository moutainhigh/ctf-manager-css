package com.ctf.utils.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum DictTypeEnum {
    ORDER_TYPE("Order Type"),
    TRANSACTION_TYPE("Transaction Type"),
    // 服务级别
    SERVICE_LEVEL("Service Level"),
    SERVICE_HOURS("Service Hours"),
    // 服务类型（COURIER，CRU，ONSITE-FRU）
    SERVICE_TYPE("Service Type"),
    PRIORITY("Priority"),
    // 1 Leg, 3 Leg
    SHIP_MODEL("Ship Model"),
    // 订单明细分类
    ITEM_CATEGORY("Item Category"),
    SHIPPING_METHOD("Shipping Method"),
    HEADER_STATUS("Header Status"),
    ITEM_STATUS("Item Status"),
    WORK_ORDER_TYPE("Workorder Type"),

    INHOUSE_GS_VENDOR("Inhouse GS Vendor"),

    GENERAL_VENDOR("General vendor")
    ;
    private String name;
}
