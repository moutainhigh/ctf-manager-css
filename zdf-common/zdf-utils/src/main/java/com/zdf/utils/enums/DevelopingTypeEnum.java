package com.zdf.utils.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 开发秘钥对的类型枚举
 *
 * @author william
 * @description
 * @Date: 2020-07-28 17:02
 */
@Getter
@AllArgsConstructor
public enum DevelopingTypeEnum {
    SUPPLIER("supplier"),
    WAREHOUSE("warehouse");
    private String type;
}
