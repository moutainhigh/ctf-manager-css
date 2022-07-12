package com.zdf.utils.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum StatusEnum {

    YES("Y"),
    NO("N"),
    ;
    private String value;
}
