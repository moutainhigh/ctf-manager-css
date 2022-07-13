package com.ctf.utils;


public interface CommonConstant {

    public static final String AR_NAME = "AR"; // FEDEX 承运商的AR节点
    public static final String AR_DESC_NAME = "Arrived at destination facility";// FEDEX 承运商的AR节点的描述
    public static final String FDXE_SHORT_NAME = "fdxe";// fedex 的简称

    public static final Integer SUPPLIER_ID = 127;// FEDEX的supplierId
    public static final Integer ORDER_TYPE_ID = 13;// route的类型id
    public static final Integer ONE_LEG = 1;// 1条腿
    public static final Integer TWO_LEG = 2;// 2条腿
    public static final Integer THREE_LEG = 3;// 3条腿

    public static final Integer MINUS_ONE_STATUSs = -1;//状态，-1不点亮
    public static final Integer ZERO_STATUS = 0;//状态，0删除，不点亮
    public static final Integer ONE_STATUS = 1;//状态，1启用，点亮
    public static final Integer TWO_STATUS = 2;//状态，2禁用


    /**
     * 节点
     */
    public static final String DN_CREATED_NAME = "DN created";
    public static final String DN_PICK_NAME = "DN pick";
    public static final String DN_GI_NAME = "DN GI";
    public static final String PICK_UP_NAME = "Pick up";
    public static final String FWD_PICKED_UP_NAME = "FWD Picked Up";
    public static final String POD_NAME = "POD";

    public static final String PICK_NAME = "PICK";
    public static final String GI_NAME = "GI";

    // msd索引数据，objectId的前缀
    public static final String MSD_PREFIX_FORTY_NAME = "40";
    public static final String MSD_PREFIX_SEVENTY_NAME = "70";

    // msd索引数据, zzsupplier的前缀
    public static final String MSD_PREFIX_THREETY_NAME = "30";

    // 通用承运商
    public static final String GENERAL_CARRIER_NAME = "General carrier";
    public static final String GENERAL_WAREHOUSE_NAME = "General warehouse";

    // fsl_location 的category
    public static final String WHOLE_UNIT_LOGISTIC_NAME = "Whole Unit Logistic";
    public static final String PART_VENDOR_NAME = "Part Vendor";
    public static final String GS_VENDOR_NAME = "GS Vendor";

    // hic的3pl
    public static final String ZZSERPROPL_THREE_NAME = "6035041676";
    public static final String ZZSERPROPL_MSD_NAME = "6002350696";
    public static final String ZZSERPROPL_MSDSSC_NAME = "6038710574";

    // 订单类型
    public static final String INBOUND_NAME = "inbound";
    public static final String OUTBOUND_NAME = "outbound";

    public static final String LENOVOCODE_NAME = "lenovoCode";
    public static final String EVENTCODE_NAME = "eventCode";
}
