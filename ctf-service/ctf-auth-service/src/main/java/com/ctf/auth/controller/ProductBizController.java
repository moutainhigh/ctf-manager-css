package com.ctf.auth.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.ctf.auth.model.bo.AddProductBizForm;
import com.ctf.auth.model.bo.QueryProductBizForm;
import com.ctf.auth.model.po.ProductBiz;
import com.ctf.auth.model.vo.ProductBizVO;
import com.ctf.auth.service.IProductBizService;
import com.ctf.common.CommonPageResult;
import com.ctf.common.CommonResult;
import com.ctf.common.enums.ResultEnum;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Map;

/**
 * <p>
 * 产品服务对应业务类型 前端控制器
 * </p>
 *
 * @author
 * @since 2020-10-27
 */
@RestController
@RequestMapping("/productBiz")
@Api(tags = "基础数据业务类型列表接口")
public class ProductBizController {

    @Autowired
    private IProductBizService productBizService;

    @ApiOperation(value = "查询业务类型列表")
    @PostMapping(value = "/findProductBizByPage")
    public CommonResult<CommonPageResult<ProductBizVO>> findProductBizByPage(@RequestBody QueryProductBizForm form) {
        IPage<ProductBizVO> pageList = productBizService.findProductBizByPage(form);
        CommonPageResult<ProductBizVO> pageVO = new CommonPageResult(pageList);
        return CommonResult.success(pageVO);
    }

    @ApiOperation(value = "新增编辑业务类型")
    @PostMapping(value = "/saveOrUpdateProductBiz")
    public CommonResult saveOrUpdateProductBiz(@Valid @RequestBody AddProductBizForm form) {
        ProductBiz productBiz = new ProductBiz().setId(form.getId())
                .setIdCode(form.getIdCode()).setName(form.getName());
        if (this.productBizService.checkUnique(productBiz)) {
            return CommonResult.error(400, "名称或代码已经存在");
        }

        if (this.productBizService.saveOrUpdateProductBiz(form)) {
            return CommonResult.success();
        } else {
            return CommonResult.error(ResultEnum.OPR_FAIL);
        }
    }

    @ApiOperation(value = "更改启用/禁用业务类型状态,id是业务类型主键")
    @PostMapping(value = "/enableOrDisableProductBiz")
    public CommonResult enableOrDisableProductBiz(@RequestBody Map<String, String> map) {

        if (StringUtils.isEmpty(map.get("id"))) {
            return CommonResult.error(500, "id is required");
        }
        Long id = Long.parseLong(map.get("id"));
        if (this.productBizService.enableOrDisableProductBiz(id)) {
            return CommonResult.success();
        } else {
            return CommonResult.error(ResultEnum.OPR_FAIL);
        }
    }

    @ApiOperation(value = "根据主键获取业务类型详情,id是业务类型主键")
    @PostMapping(value = "/getProductBizById")
    public CommonResult getProductBizById(@RequestBody Map<String, String> map) {
        if (StringUtils.isEmpty(map.get("id"))) {
            return CommonResult.error(500, "id is required");
        }
        Long id = Long.parseLong(map.get("id"));
        ProductBizVO productBizVO = this.productBizService.getById(id);
        return CommonResult.success(productBizVO);
    }
}

