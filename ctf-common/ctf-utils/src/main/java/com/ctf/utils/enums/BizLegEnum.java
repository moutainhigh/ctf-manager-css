package com.ctf.utils.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Bizleg枚举
 * @author yewei
 * @date 2022-05-31
 */
@Getter
@AllArgsConstructor
public enum BizLegEnum {

    WWH("WWH","WWHUB"),
    DC("DC","Distribution Center"),
    FSL("FSL","FSL"),
    TRP("TRP","Transportation"),
    SP("SP", "Service Provider"),

    ;
    private String code;
    private String name;

}
