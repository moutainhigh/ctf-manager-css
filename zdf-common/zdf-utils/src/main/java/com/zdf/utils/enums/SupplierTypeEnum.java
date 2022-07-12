package com.zdf.utils.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Objects;

/**
 * @author william
 * @description
 * @Date: 2020-07-22 16:39
 */
@AllArgsConstructor
@Getter
public enum SupplierTypeEnum {
    CARRIER("carrier","Carrier"),
    WAREHOUSE("warehouse","Warehouse");

    private String type;
    private String name;


    public static Boolean contains(String str) {
        for (SupplierTypeEnum value : values()) {
            if (Objects.equals(value.getType().toLowerCase(), str.toLowerCase())) {
                return true;
            }
        }
        return false;
    }
}
