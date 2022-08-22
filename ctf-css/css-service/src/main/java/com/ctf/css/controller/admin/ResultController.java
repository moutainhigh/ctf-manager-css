package com.ctf.css.controller.admin;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ctf.common.result.PageResult;
import com.ctf.css.pojo.query.InspectionPageQuery;
import com.ctf.css.pojo.query.RestultPageQuery;
import com.ctf.css.pojo.query.SelfInspectionPageQuery;
import com.ctf.css.pojo.vo.ex.InspectionResultVO;
import com.ctf.css.pojo.vo.ex.InspectionVO;
import com.ctf.css.service.InspectionResultService;
import com.ctf.css.service.SelfInspectionResultService;
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
 * @Date 2022/8/21 11:24
 * @Describe ResultController
 */
@Slf4j
@RestController
@Api(tags = "巡店督导-巡检/自检结果接口")
@RequestMapping("/store/result")
@RequiredArgsConstructor
public class ResultController {
    private final SelfInspectionResultService selfInspectionResultService;
    private final InspectionResultService inspectionResultService;

    @ApiOperation(value = "自检结果-分页列表")
    @GetMapping("/self/pages")
    public PageResult listSelfResultPages(@ApiParam(value = "自检结果搜索栏对象") RestultPageQuery queryParams) {

        // 分页查询
        Page<InspectionResultVO> result = selfInspectionResultService.pageSelfResult(queryParams);
        return PageResult.success(result);
    }

    @ApiOperation(value = "巡检结果-分页列表")
    @GetMapping("/pages")
    public PageResult listInspectionResultPages(@ApiParam(value = "巡检结果搜索栏对象") RestultPageQuery queryParams) {

        // 分页查询
        Page<InspectionResultVO> result = inspectionResultService.pageInspectionResult(queryParams);
        return PageResult.success(result);
    }
}
