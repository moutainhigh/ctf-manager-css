package com.zdf.utils.enums;

import cn.hutool.core.util.StrUtil;
import lombok.AllArgsConstructor;
import lombok.Getter;


@AllArgsConstructor
@Getter
public enum SupplierShortNameEnum {

    BTBIRD("BTBrid","BT Bird Air,BT Bird Truck",115),
    CEVA("CEVA","CEVA Air,CEVA Sea",116),
    DEWELL("DEWELL","De Well Ocean-Fast,De Well Ocean",117),
    DHL("DHL","DHL,DHL Express,DHL Express International,GLBDHL",118),
    DIMERCO("Dimerco","Dimerco",121),
    YSL("YSL","YSL",122),
    TOLL("TOLL","TOLL Sea,TOLL Air",123),
    APEX("APEX","APEX",124),
    FTN("FTN","FedEx FTN",126),
    FDEX("FDXE","FedEx Express,FedEx",127),
    DGF("DGF","DHL DGF",144),
    UPS("UPS","UPS SCS,VRYUPSTLV",188),
    DSV("DSV","DSV",212),

    ;

    private String shortName;  // 对应supplier表的shortName
    private String fwdAlias; // 对应wh_fwd_alias表的fwd_alias
    private Integer fwdSupplierId; // 对应wh_fwd_alias表的fwd_supplier_id


    public static Integer getName(String shortName){
        for (SupplierShortNameEnum value : SupplierShortNameEnum.values()){
           if (StrUtil.equalsIgnoreCase(value.shortName,shortName)) {
               return value.fwdSupplierId;
           }
        }
        return 0;
    }
}
