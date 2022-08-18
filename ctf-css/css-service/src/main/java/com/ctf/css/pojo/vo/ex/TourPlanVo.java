package com.ctf.css.pojo.vo.ex;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @Author zhangyizheng
 * @Date 2022/8/15 15:24
 * @Describe TourPlanVo
 */
@Data
public class TourPlanVo {
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
     * 行政区域
     */
    private String storeArea;

    /**
     * 店铺类型(dict_item)
     */
    private String storeType;

    /**
     * 店铺地址,多个用逗号分开
     */
    private String storePic;

    /**
     * 经度+纬度
     */
    private String lngAndLat;

    /**
     * 店铺负责人
     */
    private String storeManager;

    /**
     * 电话号码
     */
    private String storeMobile;

    /**
     * 上级负责人
     */
    private String storeSuperManager;

    /**
     * 状态(0未计划 1已计划未分配 2已分配);注：
     * 空代表，是否加入巡检计划
     * 0未计划：代表新加入计划，下一步确认独立还是联合，状态变为1
     * 1已计划未分配：设定巡店时间（按钮分配督导）
     * 2已分配：计划完成，进入任务下发页，准备启动
     */
    private String status;

    /**
     * 巡店督导员名
     */
    private String supervisorName;

    /**
     * 巡店时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime inspectionTime;

    /**
     * 巡检类型（0:独立,1:联合）
     */
    private Integer inspectionType;
}
