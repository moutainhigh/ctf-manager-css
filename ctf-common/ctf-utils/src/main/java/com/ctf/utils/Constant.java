package com.ctf.utils;

import java.util.Arrays;
import java.util.List;

public class Constant {

    public static List<String> orderTypes = Arrays.asList("ADW", "CLW", "DOA", "ECA", "FOC", "LAO", "OOW", "OPT", "PCW");

    public static List<String> bizTransTypes = Arrays.asList("YBPS", "ZCLM", "ZFSO", "YIHD", "ZRDO");

    public static List<String> itemTranactionTypes = Arrays.asList("ZBOX", "ZSP1", "ZSHM", "ZOHF", "ZRET", "ZRDP", "YPS1");

    public static List<String> serviceTypes = Arrays.asList("07", "S3", "S1", "04", "12", "06", "05", "S4", "01", "S2");

    public static List<String> servicePrioritys = Arrays.asList("6", "1", "2", "4", "5", "3", "7", "9", "8");

    public static List<String> methods = Arrays.asList("ST", "ED", "ER", "S2", "SD", "PU", "SC");

    public static List<String> seviceLevel_sla = Arrays.asList("2H", "4H", "4D", "5D", "3D", "8H", "ND", "SD", "2D");


    //es分页scroll过期时间
    public static long ES_SCROLL_TIMEOUT = 3000;


    public final static String DN_CEATE_REPEAT = "DN_create";
    public final static String DN_PICK_REPEAT = "DN_PICK";
    public final static String DN_SINGLE_PICK_REPEAT = "DN_single_pick";
    public final static String DN_PACK_REPEAT = "DN_pack";
    public final static String DN_GI_REPEAT = "DN_gi";
    public final static String DN_NOTIFY_REPEAT = "DN_notify";
    public final static String DN_PICKUP_REPEAT = "pick_up";
    public final static String DN_PENDING_PICK_REPEAT = "pending_pick";
    public final static String DN_PENDING_PACK_REPEAT = "pending_pack";
    public final static String DN_PENDING_GI_REPEAT = "pending_gi";
    public final static String PENDING_PICK_UP_REPEAT = "pending_pick_up";
    public final static String CANCEL_DN_REPEAT = "cancel_dn";
    public final static String CANCEL_DN_PACKAGE_REPEAT = "cancel_Dn_package";
    public final static String CANCEL_DN_GI_REPEAT = "cancel_Dn_gi";
    public final static String CANCEL_PICK_UP_REPEAT = "cancel_pick_up";
    public final static long REPEAT_TIMEOUT = 10;

    public final static String INBOUND_CREATE_REPEAT = "inbound_create";
    public final static String INBOUND_PICK_UP_REPEAT = "inbound_pick_up";
    public final static String INBOUND_DOCUMENT_RECEIVE_REPEAT = "inbound_document_receive";
    public final static String INBOUND_DRIVER_ARRIVE_REPEAT = "inbound_driver_arrive";
    public final static String INBOUND_GR_DATA_REPEAT = "inbound_gr_data";
    public final static String INBOUND_PENDING_GR_REPEAT = "inbound_pending_gr";
    public final static String INBOUND_PUT_AWAY_REPEAT = "inbound_put_away";
    public final static String INBOUND_CANCEL_ASN_REPEAT = "inbound_cancel_asn";


    public final static String CECCDELIVERYH_REPEAT = "CeccDeliveryH";

    public final static String CECCDELIVERYAI_REPEAT = "CeccDeliveryI";

    public final static String CECCPURCHASEORDERH_REPEAT = "CeccPurchaseOrderH";

    public final static String CECCPURCHASEORDERI_REPEAT = "CeccPurchaseOrderI";

    public final static String SECCSVSDELNOTE_REPEAT = "SeccSvSdelnote";

    public final static String CECCSALESORDERH_REPEAT = "CeccSalesOrderH";

    public final static String CECCSALESORDERI_REPEAT = "CeccSalesOrderI";

    public final static String SECCSVZMMR0005_REPEAT = "SeccSvZmmr0005";

    public final static String BIZDATACONSUMPTION_REPEAT = "BizDataConsumption";

    public final static String ZERDSDP001ECONSUMPTION_REPEAT = "ZerdSdp001eConsumption";

    public final static String FTPSN_REPEAT = "ftpSn";

    public final static String AFTER_SET = "after";

    public final static String OUTBOUND_BEGIN_FIRST = "outboundAddFirst@#$%!";

    public final static String INBOUND_BEGIN_FIRST = "INboundAddFirst@#$%!";

    public final static String TIMEZONE = "GMT+8";

    /**
     * 默认日期时间格式
     */
    public static final String DEFAULT_DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
    /**
     * 默认日期格式
     */
    public static final String DEFAULT_DATE_FORMAT = "yyyy-MM-dd";
    /**
     * 默认时间格式
     */
    public static final String DEFAULT_TIME_FORMAT = "HH:mm:ss";


    // hic的3pl
    public static final String ZZSERPROPL_THREE_NAME = "6035041676";
    public static final String ZZSERPROPL_MSD_NAME = "6002350696";
    public static final String ZZSERPROPL_MSDSSC_NAME = "6038710574";
}