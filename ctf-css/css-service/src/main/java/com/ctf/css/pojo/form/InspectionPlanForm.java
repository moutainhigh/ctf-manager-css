package com.ctf.css.pojo.form;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @Author zhangyizheng
 * @Date 2022/8/15 21:10
 * @Describe InspectionPlanForm
 */
@Data
public class InspectionPlanForm {
    /**
     * 巡检计划ID集
     */
    private List<Long> ids;

    /**
     * 督导员ID
     */
    private Long supervisorId;

    /**
     * 督导领域ID
     */
    private Long supervisorDomainId;

    /**
     * 巡店时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime inspectionTime;
}
