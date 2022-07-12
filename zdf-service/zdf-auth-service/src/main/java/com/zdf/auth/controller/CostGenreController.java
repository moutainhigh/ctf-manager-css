package com.zdf.auth.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zdf.auth.model.bo.AddCostGenreForm;
import com.zdf.auth.model.bo.QueryCostGenreForm;
import com.zdf.auth.model.po.CostGenreTaxRate;
import com.zdf.auth.model.vo.CostGenreVO;
import com.zdf.auth.service.ICostGenreTaxRateService;
import com.zdf.common.CommonPageResult;
import com.zdf.common.CommonResult;
import com.zdf.common.enums.ResultEnum;
import com.zdf.common.utils.ConvertUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.RestController;


import com.zdf.common.utils.ExcelUtils;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zdf.common.constant.SysTips;
import com.zdf.common.BaseResult;
import com.zdf.auth.service.ICostGenreService;
import com.zdf.auth.model.po.CostGenre;

import com.zdf.common.result.ListPageRuslt;
import com.zdf.common.result.PaginationBuilder;

import java.util.*;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

/**
 * 基础数据费用类型 控制类
 *
 * @author jayud
 * @since 2022-04-11
 */
@Slf4j
@Api(tags = "基础数据费用类型")
@RestController
@RequestMapping("/costGenre")
public class CostGenreController {



    @Autowired
    public ICostGenreService costGenreService;

    @Autowired
    private ICostGenreTaxRateService costGenreTaxRateService;

    /**
     * @description 分页查询
     * @author  jayud
     * @date   2022-04-11
     * @param: costGenre
     * @param: currentPage
     * @param: pageSize
     * @param: req
     * @return: com.zdf.common.BaseResult<com.baomidou.mybatisplus.core.metadata.IPage<com.zdf.auth.model.po.CostGenre>>
     **/
    @ApiOperation("分页查询数据")
    @GetMapping("/selectPage")
    public BaseResult<ListPageRuslt<CostGenre>> selectPage(CostGenre costGenre,
                                                   @RequestParam(name="currentPage", defaultValue="1") Integer currentPage,
                                                   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
                                                   HttpServletRequest req) {
        return BaseResult.ok(PaginationBuilder.buildPageResult(costGenreService.selectPage(costGenre,currentPage,pageSize,req)));
    }


    /**
    * @description 列表查询数据
    * @author  jayud
    * @date   2022-04-11
    * @param: costGenre
    * @param: req
    * @return: com.zdf.common.BaseResult<java.util.List<com.zdf.auth.model.po.CostGenre>>
    **/
    @ApiOperation("列表查询数据")
    @GetMapping("/selectList")
    public BaseResult<List<CostGenre>> selectList(CostGenre costGenre,
                                                HttpServletRequest req) {
        return BaseResult.ok(costGenreService.selectList(costGenre));
    }


    /**
    * @description 新增
    * @author  jayud
    * @date   2022-04-11
    * @param: costGenre
    * @return: com.zdf.common.BaseResult
    **/
    @ApiOperation("新增")
    @PostMapping("/add")
    public BaseResult add(@Valid @RequestBody CostGenre costGenre ){
        costGenreService.save(costGenre);
        return BaseResult.ok(SysTips.ADD_SUCCESS);
    }


    /**
     * @description 编辑
     * @author  jayud
     * @date   2022-04-11
     * @param: costGenre
     * @return: com.zdf.common.BaseResult
     **/
    @ApiOperation("编辑")
    @PostMapping("/edit")
    public BaseResult edit(@Valid @RequestBody CostGenre costGenre ){
        costGenreService.updateById(costGenre);
        return BaseResult.ok(SysTips.EDIT_SUCCESS);
    }



    /**
     * @description 物理删除
     * @author  jayud
     * @date   2022-04-11
     * @param: id
     * @return: com.zdf.common.BaseResult
     **/
    @ApiOperation("物理删除")
    @ApiImplicitParam(name = "id",value = "主键id",dataType = "Long",required = true)
    @GetMapping("/phyDel")
    public BaseResult phyDel(@RequestParam Long id){
        costGenreService.phyDelById(id);
        return BaseResult.ok(SysTips.DEL_SUCCESS);
    }

    /**
     * @description 逻辑删除
     * @author  jayud
     * @date   2022-04-11
     * @param: id
     * @return: com.zdf.common.BaseResult
     **/
    @ApiOperation("逻辑删除")
    @ApiImplicitParam(name = "id",value = "主键id",dataType = "Long",required = true)
    @GetMapping("/logicDel")
    public BaseResult logicDel(@RequestParam Long id){
        costGenreService.logicDel(id);
        return BaseResult.ok(SysTips.DEL_SUCCESS);
    }


    /**
     * @description 根据id查询
     * @author  jayud
     * @date   2022-04-11
     * @param: id
     * @return: com.zdf.common.BaseResult<com.zdf.auth.model.po.CostGenre>
     **/
    @ApiOperation("根据id查询")
    @ApiImplicitParam(name = "id",value = "主键id",dataType = "int",required = true)
    @GetMapping(value = "/queryById")
    public BaseResult<CostGenre> queryById(@RequestParam(name="id",required=true) int id) {
        CostGenre costGenre = costGenreService.getById(id);
        return BaseResult.ok(costGenre);
    }


    /**
    * @description 根据查询条件导出收货单
    * @author  jayud
    * @date   2022-04-11
    * @param: response  响应对象
    * @param: queryReceiptForm  参数queryReceiptForm
    * @param: req
    * @return: void
    **/
    @ApiOperation("根据查询条件导出基础数据费用类型")
    @PostMapping(path = "/exportCostGenre")
    public void exportCostGenre(HttpServletResponse response, @RequestParam Map<String, Object> paramMap) {
        try {
            List<String> headList = Arrays.asList(
                "主键",
                "费用类型代码",
                "费用类型名称",
                "税率",
                "描述",
                "状态（0无效 1有效）",
                "创建时间",
                "创建人",
                "更新时间",
                "更新人"
            );
            List<LinkedHashMap<String, Object>> dataList = costGenreService.queryCostGenreForExcel(paramMap);
            ExcelUtils.exportExcel(headList, dataList, "基础数据费用类型", response);
        } catch (Exception e) {
            e.printStackTrace();
            log.warn(e.toString());
        }
    }

    @ApiOperation("分页查询费用类型")
    @PostMapping("/findCostGenreByPage")
    public CommonResult<CommonPageResult<CostGenreVO>> findCostGenreByPage(@RequestBody QueryCostGenreForm form) {
        IPage<CostGenreVO> iPage = this.costGenreService.findCostGenreByPage(form);
        CommonPageResult<CostGenreVO> pageResult = new CommonPageResult<>(iPage);
        return CommonResult.success(pageResult);
    }

    /**
     * TODO 增加税率
     *
     * @param form
     * @return
     */
    @ApiOperation(value = "新增编辑费用类型")
    @PostMapping(value = "/saveOrUpdateCostGenre")
    public CommonResult saveOrUpdateCostGenre(@Valid @RequestBody AddCostGenreForm form) {
        CostGenre costGenre = new CostGenre();
        costGenre.setId(form.getId());
        costGenre.setCode(form.getCode()).setName(form.getName());
        if (this.costGenreService.checkUnique(costGenre)) {
            return CommonResult.error(400, "名称或代码已经存在");
        }
        form.checkAdd();
        if (this.costGenreService.saveOrUpdate(form)) {
            return CommonResult.success();
        } else {
            return CommonResult.error(ResultEnum.OPR_FAIL);
        }
    }

    @ApiOperation(value = "更改启用/禁用费用类型状态,id是费用类型主键")
    @PostMapping(value = "/enableOrDisableCostGenre")
    public CommonResult enableOrDisableCostGenre(@RequestBody Map<String, String> map) {
        if (StringUtils.isEmpty(map.get("id"))) {
            return CommonResult.error(500, "id is required");
        }
        Long id = Long.parseLong(map.get("id"));
        if (this.costGenreService.enableOrDisableCostGenre(id)) {
            return CommonResult.success();
        } else {
            return CommonResult.error(ResultEnum.OPR_FAIL);
        }
    }

    @ApiOperation(value = "根据主键获取费用类型详情,id是费用类型主键")
    @PostMapping(value = "/getCostGenreById")
    public CommonResult getCostGenreById(@RequestBody Map<String, String> map) {
        if (StringUtils.isEmpty(map.get("id"))) {
            return CommonResult.error(500, "id is required");
        }
        Long id = Long.parseLong(map.get("id"));
        CostGenreVO costInfo = this.costGenreService.getById(id);
        List<CostGenreTaxRate> list = this.costGenreTaxRateService.list(new QueryWrapper<>(new CostGenreTaxRate().setCostGenreId(id)));
        costInfo.setCostGenreTaxRates(list);
        return CommonResult.success(costInfo);
    }

    @ApiOperation("查询所用启用费用类型")
    @PostMapping("/getEnableCostGenre")
    public CommonResult<List<CostGenreVO>> getEnableCostGenre() {
        List<CostGenre> costGenres = this.costGenreService.getEnableCostGenre();
        List<CostGenreVO> list = new ArrayList<>();
        for (CostGenre costGenre : costGenres) {
            CostGenreVO costGenreVO = ConvertUtil.convert(costGenre, CostGenreVO.class);
            list.add(costGenreVO);
        }
        return CommonResult.success(list);
    }


}
