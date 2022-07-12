package com.zdf.utils.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum CopySupplierEnum {

    TRUE("true"),
    FALSE("false"),

    ;

    private String value;

}
