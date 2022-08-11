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
 * 大纲分类表
 * @TableName store_outline_kind
 */
@TableName(value ="store_outline_kind")
@Data
public class OutlineKind extends BaseEntity {
    /**
     * id
     */
    @TableId
    private Long id;

    /**
     * 大纲ID
     */
    private Long outlineId;

    /**
     * 分类名称
     */
    private String kindName;

    /**
     * 是否考核重点
     */
    private String isCheckKeypoint;

    /**
     * 状态(1启用 0不启用)
     */
    private String status;

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