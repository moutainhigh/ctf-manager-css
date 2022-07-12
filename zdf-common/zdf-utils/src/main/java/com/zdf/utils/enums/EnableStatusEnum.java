package com.zdf.utils.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 禁用-启用状态枚举
 * @author william
 * @description
 * @Date: 2020-07-22 12:16
 */
@AllArgsConstructor
@Getter
public enum EnableStatusEnum {
    DISABLED(0, "disabled"),
    ENABLED(1, "enabled");
    private Integer code;
    private String desc;


}
