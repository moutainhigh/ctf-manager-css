package com.ctf.css.pojo.vo.ex;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @Author zhangyizheng
 * @Date 2022/8/18 15:23
 * @Describe SelfInspectionVO 巡检结果分页结果对象
 */
@Data
public class InspectionVO {
    /**
     * 雪花ID
     */
    private Long id;

    /**
     * 店铺编码
     */
    private String storeCode;

    /**
     * 店铺名称
     */
    private String storeName;

    /**
     * 巡检类型（0:独立,1:联合，2：自检）
     */
    private Integer inspectionType;

    /**
     * 行政区域
     */
    private String storeArea;

    /**
     * 店铺类型(dict_item)
     */
    private String storeType;

    /**
     * 任务周期
     */
    private String inspectionCycle;

    /**
     * 巡检方案
     */
    private String schemeName;

    /**
     * 督导领域名
     */
    private String supervisorDomainName;

    /**
     * 店铺负责人
     */
    private String storeManager;

    /**
     * 巡店时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime inspectionTime;

    /**
     * 状态(0未启动 1进行中 2已完成);注：
     * null 门店未加入自检任务
     * 0未启动：代表已加入巡检任务，下一步确认是否启动任务，状态变为1
     * 1进行中：任务正在进行中
     * 2已完成：任务已完成
     */
    private String status;

    /**
     * 是否整改
     * 0：不需要
     * 1: 整改
     */
    private String isRectification;

}
