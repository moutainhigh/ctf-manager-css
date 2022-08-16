package com.ctf.css.controller.admin;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ctf.common.result.PageResult;
import com.ctf.common.result.Result;
import com.ctf.css.pojo.form.InspectionPlanForm;
import com.ctf.css.pojo.form.SuperviseForm;
import com.ctf.css.pojo.query.TourPlanPageQuery;
import com.ctf.css.pojo.vo.ex.TourPlanVo;
import com.ctf.css.pojo.vo.ex.UserVO;
import com.ctf.css.service.TourPlanService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @Author zhangyizheng
 * @Date 2022/8/8 11:11
 * @Describe StoreInspectionController
 */
@Slf4j
@RestController
@Api(tags = "巡店督导-门店与巡检计划接口")
@RequestMapping("/store/plan")
@RequiredArgsConstructor
public class InspectionPlanController {

    private final TourPlanService tourPlanService;

    @ApiOperation(value = "巡视计划-门店分页列表")
    @GetMapping("/pages")
    public PageResult listPlanPages(@RequestBody TourPlanPageQuery queryParams) {

        // 分页查询
        Page<TourPlanVo> result = tourPlanService.pagePlan(queryParams);
        return PageResult.success(result);
    }

    @ApiOperation(value = "加入巡检-批量-完全")
    @GetMapping("/{staffCodes}")
    public Result saveInspectionPlan(@ApiParam(value = "门店编码，多个用,分割") @PathVariable("staffCodes") @Validated String staffCodes) {
        boolean result = tourPlanService.saveInspectionPlan(staffCodes);
        return Result.judge(result);
    }

    @ApiOperation(value = "选择巡检类型-独立/联合-未计划")
    @GetMapping("/{ids}/select/{type}")
    public Result selectType(@ApiParam(value = "巡检计划ID集") @PathVariable("ids") @Validated String ids,
                             @ApiParam(value = "巡检类型") @PathVariable("type") @Validated Integer type ) {
        boolean result = tourPlanService.selectType(ids,type);
        return Result.judge(result);
    }

    @ApiOperation(value = "选择督导与巡检时间-已计划未分配")
    @PostMapping("/select")
    public Result selectSupervisor(@ApiParam(value = "巡检督导ID与巡检时间对象") @RequestBody @Validated InspectionPlanForm form) {
        boolean result = tourPlanService.selectSupervisorAndType(form);
        return Result.judge(result);
    }

    @ApiOperation(value = "任务下发-已分配")
    @PostMapping("/task/{ids}")
    public Result taskIssued(@ApiParam(value = "巡检计划ID集") @PathVariable("ids") @Validated String ids) {
        boolean result = tourPlanService.taskIssued(ids);
        return Result.judge(result);
    }

//    // TODO: 2022/8/11    @RequirePerms(value = "sys:user:delete")暂时未考虑权限问题
//    @ApiOperation(value = "删除督导人员")
//    @DeleteMapping("/{ids}")
//    public Result deleteUsers(@ApiParam("督导人员ID，多个以英文逗号(,)分割") @PathVariable String ids) {
//        boolean result = tourSupervisorService.deleteSupervisors(ids);
//        return Result.judge(result);
//    }
}
