package com.ctf.auth.controller;


import com.ctf.auth.model.enums.StatusEnum;
import com.ctf.auth.model.po.ProductClassify;
import com.ctf.auth.model.vo.ProductClassifyVO;
import com.ctf.auth.service.IProductClassifyService;
import com.ctf.common.CommonResult;
import com.ctf.common.utils.ConvertUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 产品分类 前端控制器
 * </p>
 *
 * @author 
 * @since 2020-10-29
 */
@RestController
@RequestMapping("/productClassify")
@Api("服务管理")
public class ProductClassifyController {

    @Autowired
    private IProductClassifyService productClassifyService;

    @ApiOperation(value = "查询服务类型")
    @PostMapping(value = "/getEnableParentProductClassify")
    public CommonResult<List<ProductClassifyVO>> getEnableParentProductClassify(){
        List<ProductClassify> list = this.productClassifyService.getEnableParentProductClassify(StatusEnum.ENABLE.getCode());
        List<ProductClassifyVO> result=new ArrayList<>();
        list.forEach(tmp-> result.add(ConvertUtil.convert(tmp,ProductClassifyVO.class)));
        return CommonResult.success(result);
    }
}
