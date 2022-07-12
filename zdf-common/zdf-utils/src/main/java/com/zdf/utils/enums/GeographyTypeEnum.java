package com.zdf.utils.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author william
 * @description
 * @Date: 2020-07-30 10:17
 */
@Getter
@AllArgsConstructor
public enum GeographyTypeEnum {
    COUNTRY("country"),
    STATE("state"),
    CITY("city");
    private String type;
}
