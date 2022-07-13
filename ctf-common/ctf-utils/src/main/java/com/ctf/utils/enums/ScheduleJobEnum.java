package com.ctf.utils.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;


public class ScheduleJobEnum {

    @Getter
    @AllArgsConstructor
    public enum Status{
        /**
         * 0=停止
         */
        STOP("0", "STOP"),
        /**
         * 1=运行
         */
        RUNNING("1", "RUNNING"),
        /**
         * 1=运行
         */
        PAUSE("2", "PAUSE");

        private String key;
        private String value;

    }

    @Getter
    @AllArgsConstructor
    public enum Unit{
        /**
         * 单位：分钟
         */
        MINUTES("Minutes"),
        /**
         * 单位：小时
         */
        HOURS("Hours");

        private String value;

    }

    @Getter
    @AllArgsConstructor
    public enum JobBeanClass{
        /**
         * 库存同步任务
         */
        INVENTORY_SYNC_JOB("InventorySyncJob","com.lenovo.schedule.InventorySyncJob"),
        /**
         * 更新实时订单状态
         */
        //ORDER_STATUS_SYNC_JOB("orderStatusSyncJob","com.lenovo.schedule.orderStatusSyncJob"),
        /**
         * 更新Inbound订单预警
         */
        INBOUND_WARNING_SYNC_JOB("InboundWarningSyncJob","com.lenovo.schedule.InboundWarningSyncJob"),
        /**
         * 更新Outbound订单预警
         */
        OUTBOUND_WARNING_SYNC_JOB("OutboundWarningSyncJob","com.lenovo.schedule.OutboundWarningSyncJob"),
        /**
         * 更新Shipment订单预警
         */
        SHIPMENT_WARNING_SYNC_JOB("ShipmentWarningSyncJob","com.lenovo.schedule.ShipmentWarningSyncJob"),
        /**
         * 库存同步任务
         */
        INVENTORY_SYNC_TXT_JOB("InventorySyncTxtJob","com.lenovo.schedule.InventorySyncTxtJob"),

        /**
         * 联想仓库同步任务outbound
         */
        OUTBOUND_SYNC_TXT_JOB("OutboundSyncTxtJob","com.lenovo.schedule.OutboundSyncTxtJob"),

        /**
         * 联想仓库同步任务outbound
         */
        INBOUND_SYNC_TXT_JOB("InboundSyncTxtJob","com.lenovo.schedule.InboundSyncTxtJob"),

        HUMITURE_SYN_JOB("HumitureSynJob","com.lenovo.schedule.HumitureSynJob"),

        SHIPMENT_SYN_JOB("ShipmentFwdSyncJob","com.lenovo.schedule.ShipmentFwdSyncJob"),
        ;
        private String key;
        private String value;


    }

}
