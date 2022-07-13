package com.ctf.auth.controller;

import cn.hutool.core.map.MapUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.ctf.auth.model.bo.AddCostTypeForm;
import com.ctf.auth.model.bo.QueryCostTypeForm;
import com.ctf.auth.model.enums.StatusEnum;
import com.ctf.auth.model.po.CostInfo;
import com.ctf.auth.model.vo.CostTypeVO;
import com.ctf.auth.service.ICostInfoService;
import com.ctf.common.CommonPageResult;
import com.ctf.common.CommonResult;
import com.ctf.common.constant.CommonConstant;
import com.ctf.common.constant.SqlConstant;
import com.ctf.common.entity.InitComboxStrVO;
import com.ctf.common.entity.InitComboxVO;
import com.ctf.common.enums.ResultEnum;
import com.ctf.common.utils.ConvertUtil;
import io.netty.util.internal.StringUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.RestController;


import com.ctf.common.utils.ExcelUtils;
import com.ctf.common.constant.SysTips;
import com.ctf.common.BaseResult;
import com.ctf.auth.service.ICostTypeService;
import com.ctf.auth.model.po.CostType;

import com.ctf.common.result.ListPageRuslt;
import com.ctf.common.result.PaginationBuilder;

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
 * 费用类别 控制类
 *
 * @author jayud
 * @since 2022-04-11
 */
@Slf4j
@Api(tags = "费用类别")
@RestController
@RequestMapping("/costType")
public class CostTypeController {



    @Autowired
    public ICostTypeService costTypeService;

    @Autowired
    private ICostInfoService costInfoService;


    /**
     * @description 分页查询
     * @author  jayud
     * @date   2022-04-11
     * @param: costType
     * @param: currentPage
     * @param: pageSize
     * @param: req
     * @return: com.ctf.common.BaseResult<com.baomidou.mybatisplus.core.metadata.IPage<com.ctf.auth.model.po.CostType>>
     **/
    @ApiOperation("分页查询数据")
    @GetMapping("/selectPage")
    public BaseResult<ListPageRuslt<CostType>> selectPage(CostType costType,
                                                   @RequestParam(name="currentPage", defaultValue="1") Integer currentPage,
                                                   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
                                                   HttpServletRequest req) {
        return BaseResult.ok(PaginationBuilder.buildPageResult(costTypeService.selectPage(costType,currentPage,pageSize,req)));
    }


    /**
    * @description 列表查询数据
    * @author  jayud
    * @date   2022-04-11
    * @param: costType
    * @param: req
    * @return: com.ctf.common.BaseResult<java.util.List<com.ctf.auth.model.po.CostType>>
    **/
    @ApiOperation("列表查询数据")
    @GetMapping("/selectList")
    public BaseResult<List<CostType>> selectList(CostType costType,
                                                HttpServletRequest req) {
        return BaseResult.ok(costTypeService.selectList(costType));
    }


    /**
    * @description 新增
    * @author  jayud
    * @date   2022-04-11
    * @param: costType
    * @return: com.ctf.common.BaseResult
    **/
    @ApiOperation("新增")
    @PostMapping("/add")
    public BaseResult add(@Valid @RequestBody CostType costType ){
        costTypeService.save(costType);
        return BaseResult.ok(SysTips.ADD_SUCCESS);
    }


    /**
     * @description 编辑
     * @author  jayud
     * @date   2022-04-11
     * @param: costType
     * @return: com.ctf.common.BaseResult
     **/
    @ApiOperation("编辑")
    @PostMapping("/edit")
    public BaseResult edit(@Valid @RequestBody CostType costType ){
        costTypeService.updateById(costType);
        return BaseResult.ok(SysTips.EDIT_SUCCESS);
    }



    /**
     * @description 物理删除
     * @author  jayud
     * @date   2022-04-11
     * @param: id
     * @return: com.ctf.common.BaseResult
     **/
    @ApiOperation("物理删除")
    @ApiImplicitParam(name = "id",value = "主键id",dataType = "Long",required = true)
    @GetMapping("/phyDel")
    public BaseResult phyDel(@RequestParam Long id){
        costTypeService.phyDelById(id);
        return BaseResult.ok(SysTips.DEL_SUCCESS);
    }

    /**
     * @description 逻辑删除
     * @author  jayud
     * @date   2022-04-11
     * @param: id
     * @return: com.ctf.common.BaseResult
     **/
    @ApiOperation("逻辑删除")
    @ApiImplicitParam(name = "id",value = "主键id",dataType = "Long",required = true)
    @GetMapping("/logicDel")
    public BaseResult logicDel(@RequestParam Long id){
        costTypeService.logicDel(id);
        return BaseResult.ok(SysTips.DEL_SUCCESS);
    }


    /**
     * @description 根据id查询
     * @author  jayud
     * @date   2022-04-11
     * @param: id
     * @return: com.ctf.common.BaseResult<com.ctf.auth.model.po.CostType>
     **/
    @ApiOperation("根据id查询")
    @ApiImplicitParam(name = "id",value = "主键id",dataType = "int",required = true)
    @GetMapping(value = "/queryById")
    public BaseResult<CostType> queryById(@RequestParam(name="id",required=true) int id) {
        CostType costType = costTypeService.getById(id);
        return BaseResult.ok(costType);
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
    @ApiOperation("根据查询条件导出费用类别")
    @PostMapping(path = "/exportCostType")
    public void exportCostType(HttpServletResponse response, @RequestParam Map<String, Object> paramMap) {
        try {
            List<String> headList = Arrays.asList(
                "主键ID",
                "费用类型CODE",
                "费用类型名称",
                "是否代垫代收（1-是 0-否）",
                "状态（0无效 1有效）",
                "创建时间",
                "创建人",
                "更新时间",
                "更新人",
                "描述"
            );
            List<LinkedHashMap<String, Object>> dataList = costTypeService.queryCostTypeForExcel(paramMap);
            ExcelUtils.exportExcel(headList, dataList, "费用类别", response);
        } catch (Exception e) {
            e.printStackTrace();
            log.warn(e.toString());
        }
    }

    @ApiOperation(value = "获取启用费用类型")
    @PostMapping(value = "/api/getCostTypes")
    public CommonResult<List<CostType>> getCostTypes() {
        List<CostType> costTypes = costTypeService.list(new QueryWrapper<>(new CostType().setStatus(StatusEnum.ENABLE.getCode())));
        return CommonResult.success(costTypes);
    }

    @ApiOperation(value = "费用类别,idCode=费用名称的隐藏值")
    @PostMapping(value = "/api/initCostType")
    public CommonResult<List<InitComboxVO>> initCostType(@RequestBody Map<String, Object> param) {
        String idCode = MapUtil.getStr(param, CommonConstant.ID_CODE);
        QueryWrapper queryCostInfo = new QueryWrapper();
        queryCostInfo.eq(SqlConstant.ID_CODE, idCode);
        CostInfo costInfo = costInfoService.getOne(queryCostInfo);
        if (costInfo == null || StringUtil.isNullOrEmpty(costInfo.getCids())) {
            return CommonResult.error(ResultEnum.PARAM_ERROR.getCode(), ResultEnum.PARAM_ERROR.getMessage());
        }
        String[] cids = costInfo.getCids().split(CommonConstant.COMMA);
        List<InitComboxVO> costTypeComboxs = new ArrayList<>();
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq(SqlConstant.STATUS, CommonConstant.VALUE_1);
        queryWrapper.in(SqlConstant.ID, cids);
        List<CostType> costTypes = costTypeService.list(queryWrapper);
        for (CostType costType : costTypes) {
            InitComboxVO initComboxVO = new InitComboxVO();
            initComboxVO.setName(costType.getCodeName());
            initComboxVO.setId(costType.getId());
            costTypeComboxs.add(initComboxVO);
        }
        return CommonResult.success(costTypeComboxs);
    }

    @ApiOperation(value = "查询费用类别列表")
    @PostMapping(value = "/findCostTypeByPage")
    public CommonResult<CommonPageResult<CostTypeVO>> findCostTypeByPage(@RequestBody QueryCostTypeForm form) {
        IPage<CostTypeVO> pageList = costTypeService.findCostTypeByPage(form);
        CommonPageResult<CostTypeVO> pageVO = new CommonPageResult(pageList);
        return CommonResult.success(pageVO);
    }

    @ApiOperation(value = "新增编辑费用类别")
    @PostMapping(value = "/saveOrUpdateCostType")
    public CommonResult saveOrUpdateCostType(@Valid @RequestBody AddCostTypeForm form) {
        CostType costType = new CostType();
        costType.setId(form.getId());
        costType.setCode(form.getCode()).setCodeName(form.getCodeName());
        if (this.costTypeService.checkUnique(costType)){
            return CommonResult.error(400, "名称或代码已经存在");
        }
        if (this.costTypeService.saveOrUpdateCostType(form)) {
            return CommonResult.success();
        } else {
            return CommonResult.error(ResultEnum.OPR_FAIL);
        }
    }

    @ApiOperation(value = "更改启用/禁用费用类别状态,id是费用类别主键")
    @PostMapping(value = "/enableOrDisableCostType")
    public CommonResult enableOrDisableCostType(@RequestBody Map<String,String> map) {
        if (StringUtils.isEmpty(map.get("id"))) {
            return CommonResult.error(500, "id is required");
        }
        Long id =Long.parseLong(map.get("id"));
        if (this.costTypeService.enableOrDisableCostType(id)) {
            return CommonResult.success();
        } else {
            return CommonResult.error(ResultEnum.OPR_FAIL);
        }
    }

    @ApiOperation(value = "根据主键获取费用类别详情")
    @PostMapping(value = "/getCostTypeById")
    public CommonResult getCostTypeById(@RequestBody Map<String, String> map) {
        if (StringUtils.isEmpty(map.get("id"))) {
            return CommonResult.error(500, "id is required");
        }
        Long id =Long.parseLong(map.get("id"));
        CostType costType = this.costTypeService.getById(id);
        return CommonResult.success(costType);
    }


    @ApiOperation(value = "查询所有启用费用类别")
    @PostMapping(value = "/getEnableCostType")
    public CommonResult<List<CostTypeVO>> getEnableCostType() {
        List<CostType> costTypes = this.costTypeService.getEnableCostType();
        List<CostTypeVO> list=new ArrayList<>();
        for (CostType costType : costTypes) {
            CostTypeVO costGenreVO = ConvertUtil.convert(costType, CostTypeVO.class);
            list.add(costGenreVO);
        }
        return CommonResult.success(list);
    }

    @ApiOperation(value = "下拉全部费用类别")
    @PostMapping(value = "/initAllCostType")
    public CommonResult<List<InitComboxStrVO>> initAllCostType(@RequestBody Map<String, Object> param) {
        List<CostType> enableCostType = this.costTypeService.getEnableCostType();
        List<InitComboxStrVO> list = new ArrayList<>();
        for (CostType costType : enableCostType) {
            InitComboxStrVO initComboxStrVO = new InitComboxStrVO();
            initComboxStrVO.setId(costType.getId());
            initComboxStrVO.setName(costType.getCodeName());
            initComboxStrVO.setCode(costType.getCode());
            list.add(initComboxStrVO);
        }
        return CommonResult.success(list);
    }

}
