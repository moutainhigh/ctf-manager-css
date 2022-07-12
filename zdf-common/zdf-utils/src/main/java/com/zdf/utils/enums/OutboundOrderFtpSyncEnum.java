package com.zdf.utils.enums;

import com.lenovo.DataFormatUtil;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Calendar;
import java.util.Locale;

@Getter
@AllArgsConstructor
public enum OutboundOrderFtpSyncEnum {

    DN_CREATED("DN Created", "H02X_U_DNCreated_"),
    DN_PICK("DN Pick", "H02X_U_DNPick_"),
    DN_SINGLE_PACK("DN Single Pack", "H02X_U_DNSinglePack_"),
    DN_PACKAGE("DN Package", "H02X_U_DNPackage_"),
    DN_GI("DN GI", "H02X_U_DNGI_"),
    NOTIFICATION("Notification", "H02X_U_Notification_"),
    PICK_UP("Pick Up", "H02X_U_PickUp_"),
    PENDING_PICK("Pending Pick", "H02X_U_PendingPick_"),
    PENDING_PACK("Pending Pack", "H02X_U_PendingPack_"),
    PENDING_GI("Pending GI", "H02X_U_PendingGI_"),
    PENDING_PICKUP("Pending Pickup", "H02X_U_PendingPickup_"),
    CANCELDN("CancelDN", "H02X_U_CancelDN_"),
    CANCEL_DN_PACKAGE("Cancel DN Package", "H02X_U_CancelDNPackage_"),
    CANCEL_DN_GI("Cancel DN GI", "H02X_U_CancelDNGI_"),
    CANCEL_PICK_UP("Cancel Pick Up", "H02X_U_CancelPickUp_"),
    BOOKING("Booking", "H02X_U_Booking_");

    private String key;
    private String value;

    /**
     * 根据文件目录获取对应目录文件名称
     *
     * @param str 文件路径 如/wujiang/Outbound/DN Created
     * @return ftp需要同步的文件名称
     */
    public static String getFileNameByStr(String str) {
        String today = DataFormatUtil.formatHomeTime("yyyyMMdd");
        int hour = Integer.parseInt(DataFormatUtil.formatHomeTime("HH")) - 1;
        String hourStr = String.valueOf(hour);
        if (hourStr.length() == 1) {
            hourStr = "0" + hourStr;
        }
        if (str.contains(DN_CREATED.getKey())) {
            return DN_CREATED.getValue() + today + "_" + hourStr + ".xlsx";
        } else if (str.contains(DN_PICK.getKey())) {
            return DN_PICK.getValue() + today + "_" + hourStr + ".xlsx";
        } else if (str.contains(DN_SINGLE_PACK.getKey())) {
            return DN_SINGLE_PACK.getValue() + today + ".xlsx";
        } else if (str.contains(DN_PACKAGE.getKey())) {
            return DN_PACKAGE.getValue() + today + "_" + hourStr + ".xlsx";
        } else if (str.contains(DN_GI.getKey())) {
            return DN_GI.getValue() + today + "_" + hourStr + ".xlsx";
        } else if (str.contains(NOTIFICATION.getKey())) {
            return NOTIFICATION.getValue() + today + "_" + hourStr + ".xlsx";
        } else if (str.contains(PICK_UP.getKey())) {
            return PICK_UP.getValue() + today + "_" + hourStr + ".xlsx";
        } else if (str.contains(PENDING_PICK.getKey())) {
            return PENDING_PICK.getValue() + today + "_" + hourStr + ".xlsx";
        } else if (str.contains(PENDING_PACK.getKey())) {
            return PENDING_PACK.getValue() + today + "_" + hourStr + ".xlsx";
        } else if (str.contains(PENDING_GI.getKey())) {
            return PENDING_GI.getValue() + today + "_" + hourStr + ".xlsx";
        } else if (str.contains(PENDING_PICKUP.getKey())) {
            return PENDING_PICKUP.getValue() + today + "_" + hourStr + ".xlsx";
        } else if (str.contains(CANCELDN.getKey())) {
            return CANCELDN.getValue() + today + "_" + hourStr + ".xlsx";
        } else if (str.contains(CANCEL_DN_PACKAGE.getKey())) {
            return CANCEL_DN_PACKAGE.getValue() + today + "_" + hourStr + ".xlsx";
        } else if (str.contains(CANCEL_DN_GI.getKey())) {
            return CANCEL_DN_GI.getValue() + today + "_" + hourStr + ".xlsx";
        } else if (str.contains(CANCEL_PICK_UP.getKey())) {
            return CANCEL_PICK_UP.getValue() + today + "_" + hourStr + ".xlsx";
        } else if(str.contains(BOOKING.getKey())){
            return BOOKING.getValue() + today + "_" + hourStr + ".xlsx";
        }
        return "";
    }

    public static void main(String[] args) {
        Calendar instance = Calendar.getInstance(Locale.CHINA);
    }
}
