package com.zdf.auth.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zdf.auth.model.bo.QueryForm;
import com.zdf.auth.model.vo.CodeElementsVO;
import com.zdf.auth.service.IHsElementsService;
import com.zdf.common.CommonPageResult;
import com.zdf.common.CommonResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 申报要素表 前端控制器
 * </p>
 *
 * @author LLJ
 * @since 2021-07-26
 */
@RestController
@RequestMapping("/hsElements")
@Api(tags = "申报要素管理")
public class HsElementsController {

    @Autowired
    private IHsElementsService hsElementsService;

    @ApiOperation(value = "查询所有申报要素")
    @PostMapping(value = "/findElements")
    public CommonResult findElements(@RequestBody QueryForm form) {

        IPage<CodeElementsVO> page = hsElementsService.findElements(form);
        CommonPageResult pageResult = new CommonPageResult(page);
        return CommonResult.success(pageResult);
    }

}

