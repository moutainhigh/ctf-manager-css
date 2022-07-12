package com.zdf.utils.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ApiMonitorUrlEnum {

    APEX("https://gateway.apexworkflow.com/sync-system/api/milestone"),
    CEVA("https://oneviewapi.cevalogistics.com/api/housebill?format=XML"),
    DIMERCO("https://apitest.dimerco.com/DIMTracking/getAPIList"),
    DEWELL("https://api.de-well.com/api/edi/lenovoedi315"),
    FEDEX("https://ftn.fedex.com/api/v2/shipments/track"),
    DHL("https://api.dhl.com/dgff/transportation/shipment-tracking"),
    TOLL("https://apigf-uat.tollgroup.com:9840/gateway/receiveEventDetails/1.0/EventDetails"),
    BTBIRD("http://tms.betterbt.com/api/forwarding"),
//    UPS("https://onlinetools.ups.com/ups.app/xml/Track"),
//    YSL("https://yslogi.cn/api/yspage/ordertracking/findordertracking"),
    ;

    private String url;
}
