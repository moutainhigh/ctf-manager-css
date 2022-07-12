package com.zdf.utils.enums;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 状态枚举
 */
@Getter
@AllArgsConstructor
@NoArgsConstructor
public enum IfableEnum {

    ZONE(0,"retain"), //保留
    ENABLED(1, "enabled"), //启用
    DISABLED(2, "disabled"), //禁用
    ;

    private Integer code;
    private String name;





}
