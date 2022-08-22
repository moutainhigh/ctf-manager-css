package com.ctf.css.controller.admin;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ctf.common.result.PageResult;
import com.ctf.css.pojo.query.RestultPageQuery;
import com.ctf.css.pojo.vo.ex.RectificationVO;
import com.ctf.css.service.RectificationService;
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
 * @Date 2022/8/8 14:18
 * @Describe RectificationController
 */
@Slf4j
@RestController
@Api(tags = "巡店督导-门店整改结果接口")
@RequestMapping("/store/rectification")
@RequiredArgsConstructor
public class RectificationController {

    private final RectificationService rectificationService;

    @ApiOperation(value = "整改结果-分页列表")
    @GetMapping("/pages")
    public PageResult listRectificationPages(@ApiParam(value = "搜索栏对象") RestultPageQuery queryParams) {

        // 分页查询
        Page<RectificationVO> result = rectificationService.pageRectification(queryParams);
        return PageResult.success(result);
    }
}
