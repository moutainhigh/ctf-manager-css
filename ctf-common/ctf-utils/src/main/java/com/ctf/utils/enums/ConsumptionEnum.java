package com.ctf.utils.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public enum ConsumptionEnum {

    CARRIER("carrier"),
    VAREHOUSE("warehouse");
    ;

    private String value;

}
