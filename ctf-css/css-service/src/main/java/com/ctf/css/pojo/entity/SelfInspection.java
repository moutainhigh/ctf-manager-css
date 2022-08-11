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