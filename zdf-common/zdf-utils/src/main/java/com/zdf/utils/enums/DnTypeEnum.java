package com.zdf.utils.enums;


import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum DnTypeEnum {

    DN(1,"DN"),
    PO(2,"PO"),
    ORDERID(3,"ORDERID"),
    SO(4,"SO"),
    ;

    private Integer code;
    private String value;


    @Getter
    @AllArgsConstructor
    public enum BusinessExceptionEnum{
        CONSUMPTION("consumption","consumption"),
        REPLENISHMENT("replenishment","replenishment"),

        ;
        private String module;
        private String value;
    }


    @Getter
    @AllArgsConstructor
    public enum UnderFixingEnum{

        // 异常修复中
        UNDERFIXING("Under Fixing","F"),
        ;
        private String name;
        private String message;
    }

}
