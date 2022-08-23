package com.ctf.css.controller.admin;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ctf.common.result.PageResult;
import com.ctf.css.pojo.query.DataPageQuery;
import com.ctf.css.pojo.query.TourSchemePageQuery;
import com.ctf.css.pojo.vo.ex.DataVO;
import com.ctf.css.pojo.vo.ex.TourSchemeVO;
import com.ctf.css.service.TourSchemeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author zhangyizheng
 * @Date 2022/8/23 11:52
 * @Describe DataCollectController
 */
@Slf4j
@RestController
@Api(tags = "巡店督导-数据收集")
@RequestMapping("/store/data")
@RequiredArgsConstructor
public class DataCollectController {

    private final TourSchemeService tourSchemeService;

//    @ApiOperation(value = "数据收集-分页列表")
//    @GetMapping("/pages")
//    public PageResult listDataPages(@ApiParam(value = "搜索栏对象") DataPageQuery queryParams) {
//
//        // 分页查询
//        Page<DataVO> result = tourSchemeService.pageData(queryParams);
//        return PageResult.success(result);
//    }
}
