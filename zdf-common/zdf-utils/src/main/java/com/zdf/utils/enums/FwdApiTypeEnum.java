package com.zdf.utils.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum FwdApiTypeEnum {

    NOT_CONSUMED(1),
    CONSUMED(2),
    EXCEPTION(3),
    ;
    //
    private int value;
}
