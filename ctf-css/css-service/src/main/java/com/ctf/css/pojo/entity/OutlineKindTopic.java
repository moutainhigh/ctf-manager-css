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
 * 大纲分类题库表
 * @TableName store_outline_kind_topic
 */
@TableName(value ="store_outline_kind_topic")
@Data
public class OutlineKindTopic extends BaseEntity {
    /**
     * id
     */
    @TableId
    private Long id;

    /**
     * 大纲分类ID
     */
    private String kindId;

    /**
     * 题目排序
     */
    private Integer outlineTopicNumber;

    /**
     * 题目标题
     */
    private String outlineTopicTitle;

    /**
     * 题目类型;单选，多选等类型
     */
    private String outlineTopicType;

    /**
     * 是否得分
     */
    private String isGetScore;

    /**
     * 大纲类型;0:普通项 1:红线项 2:否决项
     */
    private String optionType;

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