package com.zdf.utils.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum  MaterialMasterDataEnum {

    INSTOCK("In stock","inventory"),
    OUTOFSTOCK("Out of stock","目前无库存"),
    OOB("OOB","OOB"),
    ;

    private String key;
    private String value;

}
