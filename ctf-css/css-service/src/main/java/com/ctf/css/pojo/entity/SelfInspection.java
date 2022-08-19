package com.ctf.css.pojo.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

import com.ctf.common.base.BaseEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

/**
 * 门店自检表
 * @TableName store_self_inspection
 */
@TableName(value ="store_self_inspection")
@Data
public class SelfInspection extends BaseEntity {
    /**
     * 雪花ID
     */
    private Long id;

    /**
     * 门店ID
     */
    private Long storeId;

    /**
     * 督导领域ID
     */
    private Long superviseDomainId;

    /**
     * 巡检方案ID
     */
    private Long schemeId;

    /**
     * 巡检类型 0独立巡检 1联合巡检
     */
    private Integer inspectionType;

    /**
     * 任务周期
     */
    private String inspectionCycle;

    /**
     * 是否整改
     */
    private String isRectification;

    /**
     * 删除状态(0-未删除 1-已删除)
     */
    @TableLogic(value = "0", delval = "1")
    private Integer deleted;

    /**
     * 巡检时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime inspectionTime;

    /**
     * 状态(0未启动 1进行中 2已完成)
     */
    private String status;

    /**
     * 备注
     */
    private String remarks;

    /**
     * 创建人
     */
    private String createdBy;


    /**
     * 更新人
     */
    private String updatedBy;

}