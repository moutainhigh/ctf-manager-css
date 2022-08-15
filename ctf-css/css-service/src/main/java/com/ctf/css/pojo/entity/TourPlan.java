package com.ctf.css.pojo.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import com.ctf.common.base.BaseEntity;
import lombok.Data;

/**
 * 巡店计划表
 * @TableName store_tour_plan
 */
@TableName(value ="store_tour_plan")
@Data
public class TourPlan extends BaseEntity {
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
     * 状态(0未计划 1已计划未分配 2已分配);注：0未计划：代表新加入计划，下一步确认独立还是联合，状态变为1 1已计划未分配：设定巡店时间（按钮分配督导） 2已分配：计划完成，进入任务下发页，准备启动
     */
    private String status;

    /**
     * 删除状态(0-未删除 1-已删除)
     */
    @TableLogic(value = "0", delval = "1")
    private Integer deleted;

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