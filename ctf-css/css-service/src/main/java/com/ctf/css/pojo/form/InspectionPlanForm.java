package com.ctf.css.pojo.form;

import com.ctf.css.pojo.entity.TourSupervisor;
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
     * 巡检计划ID
     */
    private Long id;

    /**
     * 督导员
     */
    private List<TourSupervisor> supervisors;

    /**
     * 巡检类型（0:独立,1:联合）
     */
    private Integer inspectionType;

    /**
     * 巡店时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime inspectionTime;
}
