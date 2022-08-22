package com.ctf.css.pojo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 自检结果表
 * @TableName store_self_inspection_result
 */
@TableName(value ="store_self_inspection_result")
@Data
public class SelfInspectionResult implements Serializable {
    /**
     * 雪花id
     */
    @TableId
    private Long id;

    /**
     * 自检id
     */
    private String selfInspectionId;

    /**
     * 得分
     */
    private Integer score;

    /**
     * 删除状态：0-未删除，1-已删除
     */
    private Integer deleted;

    /**
     * 创建人
     */
    private Long createdBy;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新人
     */
    private Long updatedBy;

    /**
     * 更新时间
     */
    private Date updateTime;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}