package com.zdf.utils.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum SupplierKpiSettingEnum {

    Hours("Hours"),
    Days("Days"),

    Yes("Y"),
    NO("N"),

    ;
    private String value;

}
