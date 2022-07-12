package com.zdf.utils.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 出库入库运单三个界面点击eventcode时，显示追踪轨迹，用于给前端传值的枚举
 * @author william
 * @description
 * @Date: 2020-08-14 09:41
 */
@AllArgsConstructor
@Getter
/**
 * 当数据库内逻辑修改时需要修改此处
 */
public enum EventCodeTrackPointEnum {
    GREEN_POINT(0,"success", "前端判定为绿标"),
    YELLOW_POINT(1,"warning","前端判定为黄标"),
    RED_POINT(2,"error", "前端判定为红标"),
    DARK_POINT(-1,"process", "前端判定为灰标");
    private Integer key;
    private String type;
    private String describe;
}
