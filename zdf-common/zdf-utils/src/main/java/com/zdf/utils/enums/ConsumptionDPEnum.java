package com.zdf.utils.enums;


import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum  ConsumptionDPEnum {
    // header主数据
    EXTERNAL_KAFKA_H_DATA("external_kafka_h_data"),
    // item数据
    EXTERNAL_KAFKA_I_DATA("external_kafka_i_data"),
    ;

    private String name;


    @Getter
    @AllArgsConstructor
    public enum CompleteType {

        NO_COMPLETE(1),
        YES_COMPLETE(2)
        ;
        Integer status;
    }


    @Getter
    @AllArgsConstructor
    public enum ItmType{
        // itmType 类型
        ZBOX("Box launch item","ZBOX"),
        ZSHM("Shipping Model","ZSHM"),
        ZSP1("Parts","ZSP1"),
        ZRET("Return Item","ZRET"),

        ;
        private String name;
        private String value;
    }

}
