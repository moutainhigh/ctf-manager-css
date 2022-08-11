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
 * 大纲表
 * @TableName store_outline
 */
@TableName(value ="store_outline")
@Data
public class Outline extends BaseEntity {
    /**
     * id
     */
    @TableId
    private Long id;

    /**
     * 方案大纲名称
     */
    private String schemeOutlineName;

    /**
     * 方案大纲序号
     */
    private Integer schemeOutlineNumber;

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