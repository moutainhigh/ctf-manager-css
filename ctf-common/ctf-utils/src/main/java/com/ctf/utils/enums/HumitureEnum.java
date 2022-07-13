package com.ctf.utils.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum HumitureEnum {
    temperature("温度","T"),
    humidity("湿度","H"),
    ;
    public String name;
    public String value;
}
