package com.ctf.css.controller.admin;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ctf.common.result.PageResult;
import com.ctf.css.pojo.query.InspectionPageQuery;
import com.ctf.css.pojo.query.SelfInspectionPageQuery;
import com.ctf.css.pojo.vo.ex.InspectionVO;
import com.ctf.css.service.InspectionService;
import com.ctf.css.service.SelfInspectionService;
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
 * @Date 2022/8/8 14:37
 * @Describe InspectionController
 */
@Slf4j
@RestController
@Api(tags = "巡店督导-巡检与自检业务接口")
@RequestMapping("/store/inspection")
@RequiredArgsConstructor
public class InspectionController {
    private final InspectionService inspectionService;
    private final SelfInspectionService selfInspectionService;

    @ApiOperation(value = "自检-分页列表")
    @GetMapping("/self/pages")
    public PageResult listInspectionPages(@ApiParam(value = "自检搜索栏对象") SelfInspectionPageQuery queryParams) {

        // 分页查询
        Page<InspectionVO> result = selfInspectionService.pageInspection(queryParams);
        return PageResult.success(result);
    }

    @ApiOperation(value = "巡检-分页列表")
    @GetMapping("/pages")
    public PageResult listInspectionsPages(@ApiParam(value = "搜索栏对象") InspectionPageQuery queryParams) {

        // 分页查询
        Page<InspectionVO> result = inspectionService.pageInspection(queryParams);
        return PageResult.success(result);
    }

//    @ApiOperation(value = "加入巡检-批量-完全")
//    @GetMapping("/{staffCodes}")
//    public Result saveInspectionPlan(@ApiParam(value = "门店编码，多个用,分割") @PathVariable("staffCodes") @Validated String staffCodes) {
//        boolean result = tourPlanService.saveInspectionPlan(staffCodes);
//        return Result.judge(result);
//    }
}
