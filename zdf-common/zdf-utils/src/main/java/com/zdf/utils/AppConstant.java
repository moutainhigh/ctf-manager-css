package com.zdf.utils;

public interface AppConstant {

    String APPLICATION_USER_NAME = "lenovo-spider-system";

    String APPLICATION_VENDOR_NAME = "lenovo-spider-vendor";

    String APPLICATION_WH_NAME = "lenovo-spider-WH";

    String APPLICATION_ROUTE_NAME = "lenovo-spider-route";
    String APPLICATION_EXTERNAL_API_NAME = "lenovo-spider-external-api";

    String APPLICATION_REPLENISHMENT_NAME = "lenovo-spider-biz-replenishment";
    String APPLICATION_CONSUMPTION_NAME = "lenovo-spider-biz-consumption";
    String APPLICATION_HIGHVALUE_NAME = "lenovo-spider-biz-HighValue";

    String APPLICATION_EMAIL_NAME = "lenovo-spider-email";
    String APPLICATION_JOBKPI_NAME = "lenovo-spider-job-kpi";
    String APPLICATION_JOBFWD_NAME = "lenovo-spider-job-fwd";
    String APPLICATION_BI_NAME = "lenovo-spider-bi";
    String APPLICATION_DATASTORAGE_NAME = "lenovo-spider-datastorage";



    /**
     * 服务端图片验证码key
     */
    String IMAGE_CODE = "lenovo:image:code";

    String CATEGORY_TYPE_INBOUND = "inbound";
    String CATEGORY_TYPE_OUTBOUND = "outbound";
    String CATEGORY_TYPE_TRANSPORTATION = "transportation";
    String CATEGORY_TYPE_INTERNATIONAL_TRANSPORTATION = "International Transportation";
    String CATEGORY_TYPE_DOMESTIC_TRANSPORTATION = "Domestic Transportation";

    String SUPPLIER_INFO_CACHE_NAME = "supplier";

    String DICTI_NFO_CACHE_NAME = "dict:";
    String SUPPLIER_ORDER_TYPE_NFO_CACHE_NAME = "supplier:ordertype:";
    String SUPPLIER_KIP_NFO_CACHE_NAME = SUPPLIER_INFO_CACHE_NAME+":kpi";
    String USER_INFO_CACHE_NAME = "user:info";
    String USER_INFO_ROLE_CACHE_NAME = USER_INFO_CACHE_NAME+":role";
    String USER_INFO_SUPPLIER_CACHE_NAME = USER_INFO_CACHE_NAME+":supplier";
    String USER_LOGIN_INFO_CACHE_NAME = "user:login:info";
    String USER_LOGIN_MENU_CACHE_NAME = USER_INFO_CACHE_NAME+"menu";
    String SUPPLIER_API_INFO = SUPPLIER_INFO_CACHE_NAME+":api:info";

    String CONSUMPTION = "consumption";
    String CON_WORK_STATUS_INFO = CONSUMPTION+":workStatus:info";
    String CON_ROUTEID_INFO = CONSUMPTION + ":routeId:info";
    String CON_WOPARTIESLNOLVED_INFO = CONSUMPTION+":wopartieslnvolved:info";

    String SUPPLIER_ROUTERLIST_KPI_CACHE_NAME = "supplier:routerlist:kpi:";

    String USER_MENU_URLPATH_CACHE_NAME = "user:menu:urlpath";


    /**
     * 基础数据存储redis，前缀
     */
    String SYS_SUPPLIER = "sys:supplier:supplier";

    /**
     * Consumption Lscrm单同步锁
     */
    String CONSUMPTION_LSCRM_LOCK = CONSUMPTION + ":lscrm:lock:";



    /**
     *  UPS redis前缀
     */
    String UPS_NAME = "ups";
    String ASN_NAME = UPS_NAME + ":asn";
    String ORDER_CREATION_NAME = UPS_NAME + ":ordercreation";
    String PROOF_OF_DELIVERY_NAME = UPS_NAME + ":proofofdelivery";
    String ITEM_MASTER_NAME = UPS_NAME + ":itemmaster";
    String ORDER_ACKNOWLEDGMENT_NAME = UPS_NAME + ":orderacknowledgment";
    String ORDER_SHIP_CONFIRMATION_NAME = UPS_NAME + ":ordershipconfirmation";
    String ASN_RECEIPT_CONFIRMATION_NAME = UPS_NAME + ":asnreceiptconfirmation";
    String ORDER_CANCELLATION_NAME = UPS_NAME + ":ordercancellation";
}
