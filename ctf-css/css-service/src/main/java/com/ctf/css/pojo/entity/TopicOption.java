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
 * 题库选项表
 * @TableName store_topic_option
 */
@TableName(value ="store_topic_option")
@Data
public class TopicOption extends BaseEntity {
    /**
     * id
     */
    @TableId
    private Long id;

    /**
     * 大纲分类ID
     */
    private Long outlineKindId;

    /**
     * 选项内容
     */
    private String topicOptionDetails;

    /**
     * 选项对应分数
     */
    private Integer topicOptionScore;

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
    private String createdBy;


    /**
     * 更新人
     */
    private String updatedBy;


}