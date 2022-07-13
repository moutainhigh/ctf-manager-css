package com.ctf.utils.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

public enum PcgOutboundEnum {

    ;

    @AllArgsConstructor
    @Getter
    public enum CarrierEnum {
        FLEX("flex"),
        ;
        private String fwd;
    }

    @AllArgsConstructor
    @Getter
    public enum OrderTypeEnum {
        D2("D2"),
        ;
        private String value;
    }
}
