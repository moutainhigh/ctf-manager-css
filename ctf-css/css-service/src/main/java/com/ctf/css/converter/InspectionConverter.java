package com.ctf.css.converter;

import com.ctf.css.pojo.entity.Inspection;
import com.ctf.css.pojo.entity.Rectification;
import com.ctf.css.pojo.entity.TourPlan;
import org.mapstruct.Mapper;

import java.util.List;

/**
 * @Author zhangyizheng
 * @Date 2022/8/16 16:21
 * @Describe PlanToInspection
 */
@Mapper(componentModel = "spring")
public interface InspectionConverter {
    List<Inspection> planToInspection(List<TourPlan> tourPlans);

    Rectification Inspection2Rectification(Inspection inspection);
}
