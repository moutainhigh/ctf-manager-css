package com.zdf.utils.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Objects;

/**
 * 联想代码类别
 * @author william
 * @description
 * @Date: 2020-07-23 18:43
 */
@AllArgsConstructor
@Getter
public enum LenovoCodeTypeEnum {
    EVENT_CODE("event_code","EventCodes","Lenovo event code definition"),
    REASON_CODE("reason_code","ReasonCodes","Lenovo reason code definition"),
    PROCESS_CODE("process_code","Processes","Lenovo Processes definition");

    private String type;
    private String category;
    private String description;

    public static String contains(String type) {
        for (LenovoCodeTypeEnum typeEnum : values()) {
            if (Objects.equals(typeEnum.getCategory().toLowerCase(), type.toLowerCase())) {
                return typeEnum.getType();
            }
            if (Objects.equals(typeEnum.getType().toLowerCase(), type.toLowerCase())) {
                return typeEnum.getType();
            }
        }
        return null;
    }
}
