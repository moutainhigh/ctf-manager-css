package com.zdf.utils;


import cn.hutool.core.util.StrUtil;

import java.util.HashMap;
import java.util.Map;

public class CoReturnFormat {

    public static Map<String,String> messageMap = new HashMap<String, String>();

    public static void put(String key, String value) {
        if(StrUtil.isEmpty(key) || StrUtil.isEmpty(value)) {
            return;
        }
        messageMap.put(key, value);
    }

    public static void putAll(Map<String,String> m) {
        messageMap.putAll(m);
    }

    public static String getMessage(String code) {
        String message = code;
        String obj = messageMap.get(code);
        return obj==null ? message : obj;
    }

    static {
        put(CoReturnFormat.ITEMS_NULL_ERROR, "items空值异常");
        put(CoReturnFormat.PROCESSTYPE_NULL_ERROR, "processType空值异常");
        put(CoReturnFormat.ZZSERVICETYPE_NULL_ERROR, "zzserviceType空值异常");
        put(CoReturnFormat.HEAD_ROUTE_NULL_ERROR, "主节点路线异常");
        put(CoReturnFormat.SELECT_WOSTATUSDURATION_ERROR, "查找WoStatusDuration异常");
        put(CoReturnFormat.ZERDSDP001ECONSUMPTION_ERROR, "查找zerdSdp001eConsumption异常");

        put(CoReturnFormat.ZSP1_ZZSUPPLIER_NULL_ERROR, "zzsupplier空值异常");
        put(CoReturnFormat.ZSP1_ZZCARRIER_NULL_ERROR, "zzcarrier空值异常");
        put(CoReturnFormat.ZSP1_ZZSERPRO3PL_NULL_ERROR, "zzserpro3pl空值异常");
        put(CoReturnFormat.ZSP1_ZZWAYBILL_NULL_ERROR, "zzwaybill空值异常");
        put(CoReturnFormat.ZSP1_ZERDSDP001ECONSUMPTION_NULL_ERROR, "zerdSdp001eConsumption空值异常");
        put(CoReturnFormat.ZSP1_DN_NULL_ERROR, "dn空值异常");
        put(CoReturnFormat.ZSP1_DNITEM_NULL_ERROR, "dnItem空值异常");
        put(CoReturnFormat.ZSP1_ZZCARRIER_ERROR, "zzcarrier查tmsVendorCode异常");
        put(CoReturnFormat.ZSP1_ZZSUPPLIER_ERROR, "zzsupplier查tmsVendorCode异常");
        put(CoReturnFormat.ZSP1_ITEM_ROUTE_ERROR, "ZSP1子节点路线异常");
        put(CoReturnFormat.ZSP1_DNNO_NODE_ERROR, "dnNo,dnItem查不到节点时间异常");

        put(CoReturnFormat.ZSHM_ZZCARRIER_NULL_ERROR, "zzcarrier空值异常");
        put(CoReturnFormat.ZSHM_ZZWAYBILL_NULL_ERROR, "zzwaybill空值异常");
        put(CoReturnFormat.ZSHM_ITEM_ROUTE_ERROR, "ZSHM子节点路线异常");
        put(CoReturnFormat.ZSHM_ZZWAYBILL_NODE_ERROR, "zzwaybill查不到节点时间异常");

        put(CoReturnFormat.ZERT_ZZCARRIER_NULL_ERROR, "zzcarrier空值异常");
        put(CoReturnFormat.ZERT_ZZWAYBILL_NULL_ERROR, "zzwaybill空值异常");
        put(CoReturnFormat.ZERT_ITEM_ROUTE_ERROR, "ZSHM子节点路线异常");
        put(CoReturnFormat.ZERT_ZZWAYBILL_NODE_ERROR, "zzwaybill查不到节点时间异常");

        put(CoReturnFormat.ZBOX_ZZCARRIER_NULL_ERROR, "zzcarrier空值异常");
        put(CoReturnFormat.ZBOX_ZZWAYBILL_NULL_ERROR, "zzwaybill空值异常");
        put(CoReturnFormat.ZBOX_ITEM_ROUTE_ERROR, "ZSHM子节点路线异常");
        put(CoReturnFormat.ZBOX_ZZWAYBILL_NODE_ERROR, "zzwaybill查不到节点时间异常");
    }


    /** items空值异常 */
    public static final String ITEMS_NULL_ERROR = "C90001";
    /** processType空值异常 */
    public static final String PROCESSTYPE_NULL_ERROR = "C90002";
    /** zzserviceType空值异常 */
    public static final String ZZSERVICETYPE_NULL_ERROR = "C90003";
    /** 主节点路线异常 */
    public static final String HEAD_ROUTE_NULL_ERROR = "C90004";
    /** 查找WoStatusDuration异常 */
    public static final String SELECT_WOSTATUSDURATION_ERROR = "C90005";
    /** 查找zerdSdp001eConsumption异常 */
    public static final String ZERDSDP001ECONSUMPTION_ERROR = "C90006";

    //========================= zsp1  ========================================
    /** zzsupplier空值异常 */
    public static final String ZSP1_ZZSUPPLIER_NULL_ERROR = "C10001";
    /** zzcarrier空值异常 */
    public static final String ZSP1_ZZCARRIER_NULL_ERROR = "C10002";
    /** zzserpro3pl空值异常 */
    public static final String ZSP1_ZZSERPRO3PL_NULL_ERROR = "C10003";
    /** zzwaybill空值异常 */
    public static final String ZSP1_ZZWAYBILL_NULL_ERROR = "C10004";
    /** zerdSdp001eConsumption空值异常 */
    public static final String ZSP1_ZERDSDP001ECONSUMPTION_NULL_ERROR = "C10005";
    /** dn空值异常 */
    public static final String ZSP1_DN_NULL_ERROR = "C10006";
    /** dnItem空值异常 */
    public static final String ZSP1_DNITEM_NULL_ERROR = "C10007";
    /** zzcarrier查tmsVendorCode异常 */
    public static final String ZSP1_ZZCARRIER_ERROR = "C10008";
    /** zzsupplier查tmsVendorCode异常 */
    public static final String ZSP1_ZZSUPPLIER_ERROR = "C10009";
    /** ZSP1子节点路线异常 */
    public static final String ZSP1_ITEM_ROUTE_ERROR = "C10010";
    /** dnNo,dnItem查不到节点时间异常 */
    public static final String ZSP1_DNNO_NODE_ERROR = "C10011";

    //========================= zshm  ========================================
    /** zzcarrier空值异常 */
    public static final String ZSHM_ZZCARRIER_NULL_ERROR = "C11001";
    /** zzwaybill空值异常 */
    public static final String ZSHM_ZZWAYBILL_NULL_ERROR = "C11002";
    /** ZSHM子节点路线异常 */
    public static final String ZSHM_ITEM_ROUTE_ERROR = "C11003";
    /** zzwaybill查不到节点时间异常 */
    public static final String ZSHM_ZZWAYBILL_NODE_ERROR = "C11004";

    //========================= zert  ========================================
    /** zzcarrier空值异常 */
    public static final String ZERT_ZZCARRIER_NULL_ERROR = "C12001";
    /** zzwaybill空值异常 */
    public static final String ZERT_ZZWAYBILL_NULL_ERROR = "C12002";
    /** ZRET子节点路线异常 */
    public static final String ZERT_ITEM_ROUTE_ERROR = "C12003";
    /** zzwaybill查不到节点时间异常 */
    public static final String ZERT_ZZWAYBILL_NODE_ERROR = "C12004";

    //========================= zbox  ========================================
    /** zzcarrier空值异常 */
    public static final String ZBOX_ZZCARRIER_NULL_ERROR = "C13001";
    /** zzwaybill空值异常 */
    public static final String ZBOX_ZZWAYBILL_NULL_ERROR = "C13002";
    /** ZBOX子节点路线异常 */
    public static final String ZBOX_ITEM_ROUTE_ERROR = "C13003";
    /** zzwaybill查不到节点时间异常 */
    public static final String ZBOX_ZZWAYBILL_NODE_ERROR = "C13004";
}
