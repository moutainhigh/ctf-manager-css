package com.ctf.css.controller.admin;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ctf.common.result.PageResult;
import com.ctf.css.pojo.entity.Inspection;
import com.ctf.css.pojo.entity.SelfInspection;
import com.ctf.css.pojo.entity.TourPlan;
import com.ctf.css.pojo.query.SelfInspectionPageQuery;
import com.ctf.css.pojo.vo.ex.InspectionVO;
import com.ctf.css.service.InspectionService;
import com.ctf.css.service.SelfInspectionService;
import com.ctf.css.service.StoreInfoService;
import com.ctf.css.service.TourPlanService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author zhangyizheng
 * @Date 2022/8/22 15:24
 * @Describe IndexController
 */
@Slf4j
@RestController
@Api(tags = "巡店督导-首页各业务接口")
@RequestMapping("/store/index")
@RequiredArgsConstructor
public class IndexController {

    private final InspectionService inspectionService;
    private final SelfInspectionService selfInspectionService;
    private final StoreInfoService storeInfoService;
    private final TourPlanService tourPlanService;

    @ApiOperation(value = "首页")
    @GetMapping
    public PageResult countList() {
        // 2022/8/22 巡检(独立+联合)，全部门店
        int count = storeInfoService.count();
        //计划巡店
        tourPlanService.count(new LambdaQueryWrapper<TourPlan>().eq(TourPlan::getStatus,TourPlanService.UNPLANNED));
        //已计划未分配
        tourPlanService.count(new LambdaQueryWrapper<TourPlan>().eq(TourPlan::getStatus,TourPlanService.PLANNED));
        //已分配督导
        tourPlanService.count(new LambdaQueryWrapper<TourPlan>().eq(TourPlan::getStatus,TourPlanService.TASKS_ARE_ISSUED));
        //计划自检
        selfInspectionService.count(new LambdaQueryWrapper<SelfInspection>().eq(SelfInspection::getStatus,InspectionService.UNSTART));
        //todo 任务下发=计划自检+巡检，以下为巡检
        inspectionService.count();
        //完成巡检
        inspectionService.count(new LambdaQueryWrapper<Inspection>().eq(Inspection::getStatus,InspectionService.COMPLETED));
        //待整改
        inspectionService.count(new LambdaQueryWrapper<Inspection>().eq(Inspection::getIsRectification,"1"));
        return null;
    }
}
