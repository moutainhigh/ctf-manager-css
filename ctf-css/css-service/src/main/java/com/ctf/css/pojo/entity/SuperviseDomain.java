package com.ctf.css.pojo.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.util.Date;

import com.ctf.common.base.BaseEntity;
import lombok.Data;

/**
 * 督导领域表
 * @TableName store_supervise_domain
 */
@TableName(value ="store_supervise_domain")
@Data
public class SuperviseDomain extends BaseEntity {
    /**
     * 雪花ID
     */
    @TableId
    private Long id;

    /**
     * 领域名称
     */
    private String superviseDomainName;

    /**
     * 状态(1:启用 0:禁用)
     */
    private String status;

    /**
     * 删除状态：0-未删除，1-已删除
     */
    @TableLogic(value = "0", delval = "1")
    private Integer isDeleted;

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