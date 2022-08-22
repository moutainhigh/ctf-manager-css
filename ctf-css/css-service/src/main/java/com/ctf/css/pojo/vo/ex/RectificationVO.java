package com.ctf.css.pojo.vo.ex;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @Author zhangyizheng
 * @Date 2022/8/22 11:14
 * @Describe RectificationVO
 */
@Data
public class RectificationVO {
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
     * 督导
     */
    private String supervisor;

    /**
     * 巡店时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime inspectionTime;

    /**
     * 整改项
     */
    private String rectificationDetails;

    /**
     * 状态(0未启动 1进行中 2已完成)
     */
    private String status;
}
