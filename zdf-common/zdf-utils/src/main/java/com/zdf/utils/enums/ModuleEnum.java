package com.zdf.utils.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ModuleEnum {

    CONSUMPTION("consumption"),
    REPLENISHMENT("replenishment"),

    ;

    private String type;
}
