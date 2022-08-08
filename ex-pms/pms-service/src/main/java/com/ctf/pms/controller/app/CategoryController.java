package com.ctf.pms.controller.app;

import com.ctf.common.result.Result;
import com.ctf.pms.pojo.vo.CategoryVO;
import com.ctf.pms.service.IPmsCategoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 商品分类控制器
 *
 * @author H.m
 * @date 2022/2/5
 */
@Api(tags = "「移动端」商品分类")
@RestController("appCategoryController")
@RequestMapping("/app-api/v1/categories")
@RequiredArgsConstructor
public class CategoryController {

    private final IPmsCategoryService iPmsCategoryService;

    @ApiOperation(value = "分类列表")
    @GetMapping
    public Result list(
           @ApiParam("上级分类ID") Long parentId) {
        List<CategoryVO> list = iPmsCategoryService.listCategory(parentId);
        return Result.success(list);
    }
}
