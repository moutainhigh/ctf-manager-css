package com.ctf.utils.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum ExternalApiEnum {
    ;

    @AllArgsConstructor
    @Getter
    public enum ShipmentStatusEnum {

        BOOKING_TIME(10,"BOOKING_TIME"),
        PICKUP_TIME(20,"PICKUP_TIME"),
        POD_TIME(30,"POD_TIME"),
        CANCEL_TIME(99,"CANCEL_TIME"),

        ;
        private Integer code;
        private String message;
    }

}
