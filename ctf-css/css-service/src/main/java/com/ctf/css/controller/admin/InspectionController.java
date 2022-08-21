package com.ctf.css.controller.admin;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ctf.common.result.PageResult;
import com.ctf.common.result.Result;
import com.ctf.css.pojo.dto.InspectionDTO;
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
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

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

    @ApiOperation(value = "加入自检-批量-未启动")
    @GetMapping("/{staffCodes}")
    public Result saveSelfInspection(@ApiParam(value = "门店编码，多个用,分割") @PathVariable("staffCodes") @Validated String staffCodes) {
        boolean result = selfInspectionService.saveSelfInspection(staffCodes);
        return Result.judge(result);
    }

    @ApiOperation(value = "未启动-启动-进行中")
    @PostMapping("/start")
    public Result startInspection(@ApiParam(value = "门店任务未启动对象（ID+类型）") @RequestBody InspectionDTO dto) {
        boolean result = inspectionService.startInspection(dto);
        return Result.judge(result);
    }

    @ApiOperation(value = "未启动-删除")
    @DeleteMapping("/delete")
    public Result deleteInspection(@ApiParam(value = "门店任务未启动对象（ID+类型）") @RequestBody InspectionDTO dto) {
        boolean result = inspectionService.deleteInspection(dto);
        return Result.judge(result);
    }

//    @ApiOperation(value = "进行中-停止")
//    @PostMapping("/stop")
//    public Result stopInspection(@ApiParam(value = "门店任务未启动对象（ID+类型）") @RequestBody InspectionDTO dto) {
//        boolean result = inspectionService.stopInspection(dto);
//        return Result.judge(result);
//    }
}
