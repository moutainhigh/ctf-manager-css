package com.ctf.css.pojo.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.util.Date;

import com.ctf.common.base.BaseEntity;
import lombok.Data;

/**
 * 巡检方案表
 * @TableName store_tour_scheme
 */
@TableName(value ="store_tour_scheme")
@Data
public class TourScheme extends BaseEntity {
    /**
     * id
     */
    @TableId
    private Long id;

    /**
     * 方案编号
     */
    private String schemeCode;

    /**
     * 督导领域ID
     */
    private Long superviseDomainId;

    /**
     * 方案名称
     */
    private String schemeName;

    /**
     * 方案大纲ID
     */
    private Long schemeOutlineId;

    /**
     * 题目数量
     */
    private String schemeTopicNum;

    /**
     * 状态(0未收集 1收集中 2已停止，方案不可用)
     */
    private String status;

    /**
     * 删除状态：0-未删除，1-已删除
     */
    @TableLogic(value = "0", delval = "1")
    private Integer isDeleted;

    /**
     * 描述
     */
    private String remarks;

    /**
     * 创建人
     */
    private Long createdBy;


    /**
     * 更新人
     */
    private Long updatedBy;


}