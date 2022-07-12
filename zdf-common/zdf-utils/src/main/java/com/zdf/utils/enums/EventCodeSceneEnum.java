package com.zdf.utils.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 事件EventCode应用场景
 * @author weihuang.huang
 * @description
 * @Date: 2020-08-29 14:01
 */
@AllArgsConstructor
@Getter
public enum EventCodeSceneEnum {
    /**
     * 默认预设的事件EventCode
     */
    DEFAULT("10", "10"),
    /**
     * 通过路线设置的事件EventCode
     */
    ROUTER("20", "20");
    private String key;
    private String value;

}
