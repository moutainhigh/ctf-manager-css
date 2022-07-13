package com.ctf.utils.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

@AllArgsConstructor
@Getter
public enum WorkOrderTypeEnum {

    CCI("carry-in","Carry-in"),
    DPT("depot","Depot"),
    ONS("onsite","Onsite"),
    ASP("awsp","AWSP"),
    CRU("cru","CRU"),
    PTS("part sales","Part Sales"),
    ;

    private String name;
    private String type;


    private static Map<String,WorkOrderTypeEnum> nameMap = new HashMap<>();
    private static Map<String,WorkOrderTypeEnum> typeMap = new HashMap<>();

    static {
        for (WorkOrderTypeEnum gender : WorkOrderTypeEnum.values()){
            nameMap.put(gender.name,gender);
            typeMap.put(gender.type,gender);
        }
    }

    public static String getByName(String name){
        WorkOrderTypeEnum result = nameMap.get(name);
        if (null != result){
            return result.type;
        }
        return null;
    }

    public static String getByType(String type){
        WorkOrderTypeEnum result = typeMap.get(type);
        if (null != result){
            return result.name;
        }
        return null;
    }

}
