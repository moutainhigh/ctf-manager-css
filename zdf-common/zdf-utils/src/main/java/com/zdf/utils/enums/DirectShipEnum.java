package com.zdf.utils.enums;


import lombok.AllArgsConstructor;
import lombok.Getter;


public class DirectShipEnum {

    @Getter
    @AllArgsConstructor
    public enum BizScenario{
        IPR("IPR"),
        NORMAL("Normal"),
        ;

        private String type;
    }

    @Getter
    @AllArgsConstructor
    public enum IPR{

        ONE("WH-PoU"),
        TWO("WH-PUDO"),
        ;

        private String value;
    }

}
