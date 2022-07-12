package com.zdf.utils.enums;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum LenovoOrderTypeEnum {

    ONE(1,"Inbound incl."),
    TOW(2,"Outbound incl."),
    THREE(3,"FWD incl.")
    ;

    private Integer code;
    private String value;

    public static String getName(Integer index){
        for (LenovoOrderTypeEnum value : LenovoOrderTypeEnum.values()){
            if (value.getCode() == index){
                return value.getValue();
            }
        }
        return "";
    }



}
