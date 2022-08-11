package com.ctf.css.pojo.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.util.Date;

import com.ctf.common.base.BaseEntity;
import lombok.Data;

/**
 * 巡店督导员表
 * @TableName store_tour_supervisor
 */
@TableName(value ="store_tour_supervisor")
@Data
public class TourSupervisor extends BaseEntity {
    /**
     * id
     */
    @TableId
    private Long id;

    /**
     * 用户id
     */
    private Long userId;

    /**
     * 督导领域id
     */
    private Long superviseDomainId;

    /**
     * 状态(1启用 0禁用)
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