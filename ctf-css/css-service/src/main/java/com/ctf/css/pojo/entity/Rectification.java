package com.ctf.css.pojo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;

import com.ctf.common.base.BaseEntity;
import lombok.Data;

/**
 * 门店整改表
 * @TableName store_rectification
 */
@TableName(value ="store_rectification")
@Data
public class Rectification extends BaseEntity {
    /**
     * 雪花ID
     */
    @TableId
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
     * 督导员ID
     */
    private Long superviseId;

    /**
     * 巡检类型 0独立巡检 1联合巡检
     */
    private String inspectionType;

    /**
     * 巡检周期
     */
    private String inspectionCycle;

    /**
     * 巡检方案ID
     */
    private Long schemeId;

    /**
     * 整改项
     */
    private String rectificationDetails;

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
    private Long createdBy;


    /**
     * 更新人
     */
    private Long updatedBy;


}