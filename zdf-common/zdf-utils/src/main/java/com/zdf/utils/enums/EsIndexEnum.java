package com.zdf.utils.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum EsIndexEnum {
    // 大数据平台-item数据
    BIZDATACONSUMPTIONINDEX("biz_data_consumption","biz_data_consumption"),
    BIZDATACONSUMPTIONINDEX_COPY("biz_data_consumption_copy","biz_data_consumption_copy"),
    // 大数据平台-item10月到11月数据
    BIZDATACONSUMPTIONINDEX_NEW("biz_data_consumption_new","biz_data_consumption_new"),
    CECCDELIVERYHINDEX("cecc_delivery_h_copy","cecc_delivery_h_copy"),
    CECCDELIVERYIINDEX("cecc_delivery_i_copy","cecc_delivery_i_copy"),
    CECCSALESORDERHIINDEX("cecc_sales_order_i_copy","cecc_sales_order_i_copy"),
    CECCSALESORDERIINDEX("cecc_sales_order_h_copy","cecc_sales_order_h_copy"),
    FTPSNINDEX("ftp_sn_copy","ftp_sn_copy"),
    CECCPURCHASEORDERHINDEX("cecc_purchase_order_h_copy","cecc_purchase_order_h_copy"),
    CECCPURCHASEORDERIINDEX("cecc_purchase_order_i_copy","cecc_purchase_order_i_copy"),
    SECCSVSDELNOTEINDEX("secc_sv_sdelnote_key","secc_sv_sdelnote_key"),
    SECCSVZMMR0005INDEX("secc_sv_zmmr0005","secc_sv_zmmr0005"),
    // 大数据平台-dn数据
    ZERDSDP001ECONSUMPTIONINDEX("zerd_sdp001e_consumption","zerd_sdp001e_consumption"),
    ZERDSDP001ECONSUMPTIONCOPYINDEX("zerd_sdp001e_consumption_copy","zerd_sdp001e_consumption_copy"),
    ZERDSDP001ECONSUMPTIONINDEXIBM("zerd_sdp001e_consumption_ibm","zerd_sdp001e_consumption_ibm"),
    DATAMODELINDEX("datamodel","datamodel"),
    HUMITUREHISTORYINDEX("humiturehistory","humiturehistory"),
    FTPFILEHISTORYINDEX("ftp_file_history","ftp_file_history"),
    SPIDER_API_HISTORY_INDEX("spider_api_history","spider_api_history"),
    INBOUND_ORDER_INDEX("inbound_order","inbound_order"),
    OUTBOUND_ORDER_INDEX("outbound_order","outbound_order"),
    INVENTORY_INDEX("inventory","inventory"),
    INVENTORY_COPY_INDEX("inventory_copy","inventory_copy"),
    INBOUND_EVENT_INDEX("inbound_event","inbound_event"),
    OUTBOUND_EVENT_INDEX("outbound_event","outbound_event"),
    CECC_ADDRESS_INDEX("cecc_address","cecc_address"),
    REPLENISHMENT_INHOUSE_DP_INDEX("replenishment_inhouse_dp","replenishment_inhouse_dp"),
    WO_STATUS_DURATION_INDEX("wo_status_duration","wo_status_duration"),
    // 联想kefka原始数据
    SPIDER_API_KAFKA_RECORD_INDEX("spider_api_kafka_record_index","spider_api_kafka_record_index"),
    CONSUM_MSD_CRM_BPARTNER_ATTR_INDEX("msd_crm_bpartner_attr","msd_crm_bpartner_attr"),
    // Consumption-header主数据
    CONSUM_MSD_CRM_SRV_PROCESS_H_INDEX("msd_crm_srv_process_h","msd_crm_srv_process_h"),
    // Consumption-item数据
    CONSUM_MSD_CRM_SRV_PROCESS_I_INDEX("msd_crm_srv_process_i","msd_crm_srv_process_i"),
    KAFKA_CON_MONITOR("kafka_con_monitor","kafka_con_monitor"),
    KAFKA_CON_ERROR_MONITOR("kafka_con_error_monitor","kafka_con_error_monitor"),

    INBOUND_ORDER_STATUS_TRACK_INDEX("inbound_order_status_track","inbound_order_status_track"),
    OUTBOUND_ORDER_STATUS_TRACK_INDEX("outbound_order_status_track","outbound_order_status_track"),

    PCG_OUTBOUND_COPY_INDEX("pcg_outbound_copy","doc"),
    WO_PARTIESINVOLVED_INDEX("wo_parties_involved","doc"),
    SSC_CONSUMPTION_REPORT("ssc_consumption_report","ssc_consumption_report"),
    // msd-dn数据
    SSC_SERVICE_CONSUME_ORDER("ssc_service_consume_order","ssc_service_consume_order"),

    SPIDER_ACTIVE_USERS("spider_active_users","spider_active_users"),
    // 模块数据
    SPIDER_STATUS_HISTORY("spider_status_history","_doc"),
    ;
    String indexName;

    String indexType;
}
