package com.ctf.css.controller.admin;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ctf.common.result.PageResult;
import com.ctf.common.result.Result;
import com.ctf.css.pojo.entity.TourScheme;
import com.ctf.css.pojo.query.TourSchemePageQuery;
import com.ctf.css.pojo.vo.ex.TourSchemeVO;
import com.ctf.css.service.TourSchemeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

/**
 * @Author zhangyizheng
 * @Date 2022/8/22 16:21
 * @Describe TourSchemeController
 */
@Slf4j
@RestController
@Api(tags = "巡店督导-巡检方案接口")
@RequestMapping("/store/scheme")
@RequiredArgsConstructor
public class TourSchemeController {
    private final TourSchemeService tourSchemeService;

    @ApiOperation(value = "方案管理-分页列表")
    @GetMapping("/pages")
    public PageResult listTourSchemePages(@ApiParam(value = "搜索栏对象")TourSchemePageQuery queryParams) {

        // 分页查询
        Page<TourSchemeVO> result = tourSchemeService.pageTourScheme(queryParams);
        return PageResult.success(result);
    }

    @ApiOperation(value = "方案管理-预览")
    @GetMapping("/one/{id}")
    public Result getTourSchemeById(@ApiParam(value = "方案ID") @PathVariable("id") Long id) {

        TourSchemeVO result = tourSchemeService.getOneById(id);
        return Result.success(result);
    }

//    @ApiOperation(value = "方案管理-新增")
//    @PostMapping("/add}")
//    public Result saveTourScheme(@ApiParam(value = "新增对象") @RequestBody TourSchemeForm form) {
//
//        boolean result = tourSchemeService.saveTourScheme(form);
//        return Result.judge(result);
//    }

    // TODO: 2022/8/23 停止的业务不太明确
//    @ApiOperation(value = "方案管理-停止")
//    @GetMapping("/stop/{id}")
//    public Result stopTourScheme(@ApiParam(value = "方案ID") @PathVariable("id") Long id) {
//
//        boolean result = tourSchemeService.stopOne(id);
//        return Result.judge(result);
//    }

    @ApiOperation(value = "方案管理-删除")
    @DeleteMapping("/delete/{id}")
    public Result deleteTourScheme(@ApiParam(value = "方案ID") @PathVariable("id") Long id) {
        boolean result = tourSchemeService.deleteOne(id);
        return Result.judge(result);
    }
}
