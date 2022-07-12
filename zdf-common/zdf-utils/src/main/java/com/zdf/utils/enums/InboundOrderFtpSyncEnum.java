package com.zdf.utils.enums;

import cn.hutool.core.util.StrUtil;
import com.lenovo.DataFormatUtil;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum InboundOrderFtpSyncEnum {
    ASN_RECEIVE("ASN Receive","H02X_U_ASNReceive_"),
    PICKUP("Pickup","H02X_U_Pickup_"),
    DOCUMENT_RECEIVE("Document Receive","H02X_U_DocumentReceive_"),
    DRIVER_ARRIVE("Driver arrive","H02X_U_Driverarrive_"),
    GR_DATE("GR Date","H02X_U_GRDate_"),
    PENDING_GR("Pending GR","H02X_U_PendingGR_"),
    PUT_AWAY("Put Away","H02X_U_PutAway_"),
    CANCELASN("CancelASN","H02X_U_CancelASN_"),
    ;
    String key;
    String value;

    public static String getFileNameByStr(String supplierFilePath) {
        String today = DataFormatUtil.formatHomeTime("yyyyMMdd");
        int hour = Integer.parseInt(DataFormatUtil.formatHomeTime("HH")) - 1;
        String hourStr = String.valueOf(hour);
        if (hourStr.length() == 1) {
            hourStr = "0" + hourStr;
        }
        if(StrUtil.contains(supplierFilePath,ASN_RECEIVE.getKey())){
            return ASN_RECEIVE.getValue()+today+"_"+hourStr+".xlsx";
        }else if(StrUtil.contains(supplierFilePath,PICKUP.getKey())){
            return PICKUP.getValue()+today+"_"+hourStr+".xlsx";
        }else if(StrUtil.contains(supplierFilePath,DOCUMENT_RECEIVE.getKey())){
            return DOCUMENT_RECEIVE.getValue()+today+"_"+hourStr+".xlsx";
        }else if(StrUtil.contains(supplierFilePath,DRIVER_ARRIVE.getKey())){
            return DRIVER_ARRIVE.getValue()+today+"_"+hourStr+".xlsx";
        }else if(StrUtil.contains(supplierFilePath,GR_DATE.getKey())){
            return GR_DATE.getValue()+today+"_"+hourStr+".xlsx";
        }else if(StrUtil.contains(supplierFilePath,PENDING_GR.getKey())){
            return PENDING_GR.getValue()+today+"_"+hourStr+".xlsx";
        }else if(StrUtil.contains(supplierFilePath,PUT_AWAY.getKey())){
            return PUT_AWAY.getValue()+today+"_"+hourStr+".xlsx";
        }else if(StrUtil.contains(supplierFilePath,CANCELASN.getKey())){
            return CANCELASN.getValue()+today+"_"+hourStr+".xlsx";
        }
        return null;
    }
}
