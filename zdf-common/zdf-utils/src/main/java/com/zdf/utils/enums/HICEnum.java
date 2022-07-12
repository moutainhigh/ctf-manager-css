package com.zdf.utils.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum HICEnum {
    ;

    @AllArgsConstructor
    @Getter
    public enum OutStatusEnum {
        CANCEL(99,"Cancel"),
        PICK(10,"pick"),
        PACK(20,"pack"),

        ;
        private Integer code;
        private String message;
    }

    @AllArgsConstructor
    @Getter
    public enum InStatusEnum {
        CANCEL(99,"Cancel"),
        GR(10,"GR"),

        ;
        private Integer code;
        private String message;
    }
}
