package com.zdf.utils.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public enum JobFwdEnum {

    APEX("apex"),
    BTBIRD("btbird"),
    CEVA("ceva"),
    DEWELL("dewell"),
    DGF("dgf"),
    DHL("dhl"),
    DIMERCO("dimerco"),
    FEDEXEXPRESS("fedexExpress"),
    FEDEXFTN("fedexFtn"),
    FEDEX("Fedex"),
    FTN("FTN"),
    TOLL("toll"),
    UPS("ups"),
    YSL("ysl"),
    DSV("dsv"),

    ;

    private String value;

}
