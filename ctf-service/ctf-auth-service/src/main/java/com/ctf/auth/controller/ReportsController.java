package com.ctf.auth.controller;

import com.ctf.auth.model.bo.AddReportsForm;
import com.ctf.auth.model.bo.DeleteForm;
import com.ctf.auth.model.bo.QueryForm;
import com.ctf.auth.model.vo.ReportsVO;
import com.ctf.common.CommonResult;
import com.ctf.common.utils.ConvertUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;


import com.ctf.common.utils.ExcelUtils;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.ctf.common.constant.SysTips;
import com.ctf.common.BaseResult;
import com.ctf.auth.service.IReportsService;
import com.ctf.auth.model.po.Reports;

import com.ctf.common.result.ListPageRuslt;
import com.ctf.common.result.PaginationBuilder;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

/**
 *  控制类
 *
 * @author jayud
 * @since 2022-04-25
 */
@Slf4j
@Api(tags = "")
@RestController
@RequestMapping("/reports")
public class ReportsController {



    @Autowired
    public IReportsService reportsService;


    /**
     * @description 分页查询
     * @author  jayud
     * @date   2022-04-25
     * @param: reports
     * @param: currentPage
     * @param: pageSize
     * @param: req
     * @return: com.ctf.common.BaseResult<com.baomidou.mybatisplus.core.metadata.IPage<com.ctf.auth.model.po.Reports>>
     **/
    @ApiOperation("分页查询数据")
    @GetMapping("/selectPage")
    public BaseResult<ListPageRuslt<Reports>> selectPage(Reports reports,
                                                   @RequestParam(name="currentPage", defaultValue="1") Integer currentPage,
                                                   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
                                                   HttpServletRequest req) {
        return BaseResult.ok(PaginationBuilder.buildPageResult(reportsService.selectPage(reports,currentPage,pageSize,req)));
    }


    /**
    * @description 列表查询数据
    * @author  jayud
    * @date   2022-04-25
    * @param: reports
    * @param: req
    * @return: com.ctf.common.BaseResult<java.util.List<com.ctf.auth.model.po.Reports>>
    **/
    @ApiOperation("列表查询数据")
    @GetMapping("/selectList")
    public BaseResult<List<Reports>> selectList(Reports reports,
                                                HttpServletRequest req) {
        return BaseResult.ok(reportsService.selectList(reports));
    }


    /**
    * @description 新增
    * @author  jayud
    * @date   2022-04-25
    * @param: reports
    * @return: com.ctf.common.BaseResult
    **/
    @ApiOperation("新增")
    @PostMapping("/add")
    public BaseResult add(@Valid @RequestBody Reports reports ){
        reportsService.save(reports);
        return BaseResult.ok(SysTips.ADD_SUCCESS);
    }


    /**
     * @description 编辑
     * @author  jayud
     * @date   2022-04-25
     * @param: reports
     * @return: com.ctf.common.BaseResult
     **/
    @ApiOperation("编辑")
    @PostMapping("/edit")
    public BaseResult edit(@Valid @RequestBody Reports reports ){
        reportsService.updateById(reports);
        return BaseResult.ok(SysTips.EDIT_SUCCESS);
    }



    /**
     * @description 物理删除
     * @author  jayud
     * @date   2022-04-25
     * @param: id
     * @return: com.ctf.common.BaseResult
     **/
    @ApiOperation("物理删除")
    @ApiImplicitParam(name = "id",value = "主键id",dataType = "Long",required = true)
    @GetMapping("/phyDel")
    public BaseResult phyDel(@RequestParam Long id){
        reportsService.phyDelById(id);
        return BaseResult.ok(SysTips.DEL_SUCCESS);
    }

    /**
     * @description 逻辑删除
     * @author  jayud
     * @date   2022-04-25
     * @param: id
     * @return: com.ctf.common.BaseResult
     **/
    @ApiOperation("逻辑删除")
    @ApiImplicitParam(name = "id",value = "主键id",dataType = "Long",required = true)
    @PostMapping("/logicDel")
    public BaseResult logicDel(@RequestBody DeleteForm form){
        reportsService.logicDel(form.getIds());
        return BaseResult.ok(SysTips.DEL_SUCCESS);
    }


    /**
     * @description 根据id查询
     * @author  jayud
     * @date   2022-04-25
     * @param: id
     * @return: com.ctf.common.BaseResult<com.ctf.auth.model.po.Reports>
     **/
    @ApiOperation("根据id查询")
    @ApiImplicitParam(name = "id",value = "主键id",dataType = "int",required = true)
    @GetMapping(value = "/queryById")
    public BaseResult<Reports> queryById(@RequestParam(name="id",required=true) int id) {
        Reports reports = reportsService.getById(id);
        return BaseResult.ok(reports);
    }


    /**
    * @description 根据查询条件导出收货单
    * @author  jayud
    * @date   2022-04-25
    * @param: response  响应对象
    * @param: queryReceiptForm  参数queryReceiptForm
    * @param: req
    * @return: void
    **/
    @ApiOperation("根据查询条件导出")
    @PostMapping(path = "/exportReports")
    public void exportReports(HttpServletResponse response, @RequestParam Map<String, Object> paramMap) {
        try {
            List<String> headList = Arrays.asList(
                "自动ID",
                "模块ID",
                "模块名称",
                "报表显示名称",
                "报表数径",
                "报表文件名",
                "数据源参数",
                "数据源名称",
                "是否纵向或者横向，true为纵，false为横向",
                "是否有子报表,true：有，false没有",
                "表报参数名，多个中间用英文逗号隔开",
                "菜单名ID",
                "菜单名代码",
                "备注",
                "组织机构ID",
                "多租户id",
                "多租户code",
                "创建人",
                "创建人名称",
                "创建时间",
                "最后修改人",
                "最后修改人名称",
                "最后修改时间",
                "删除标志",
                "删除人",
                "删除人名称",
                "删除时间"
            );
            List<LinkedHashMap<String, Object>> dataList = reportsService.queryReportsForExcel(paramMap);
            ExcelUtils.exportExcel(headList, dataList, "", response);
        } catch (Exception e) {
            e.printStackTrace();
            log.warn(e.toString());
        }
    }

    @ApiOperation(value = "根据菜单code获取所有打印报表模板")
    @PostMapping(value = "/getReportsByMenuCode")
    public CommonResult<List<ReportsVO>> getReportsByMenuCode(@RequestBody QueryForm form) {
        List<ReportsVO> reportsVOList = reportsService.getReportsByMenuCode(form.getActionCode());
        return CommonResult.success(reportsVOList);
    }

    @ApiOperation(value = "根据id获取打印报表信息")
    @PostMapping(value = "/getReportsById")
    public CommonResult<ReportsVO> getReportsById(@RequestBody QueryForm form) {
        Reports reports = reportsService.getById(form.getId());
        ReportsVO reportsVO = ConvertUtil.convert(reports, ReportsVO.class);
        return CommonResult.success(reportsVO);
    }

    @ApiOperation(value = "新增或修改打印报表")
    @PostMapping(value = "/saveOrUpdateReports")
    public CommonResult saveOrUpdateReports(@RequestBody AddReportsForm form) {
        boolean result = reportsService.saveOrUpdateReports(form);
        if(!result){
            return CommonResult.error(444,"新增或修改打印报表失败");
        }
        return CommonResult.success();
    }

    @ApiOperation(value = "根据id获取报表请求地址信息")
    @PostMapping(value = "/getUrlById")
    public CommonResult getUrlById(@RequestBody QueryForm form) {
        Reports reports = reportsService.getById(form.getId());
        String rptPath = reports.getRptPath();
        String paraStr = reports.getParaStr();
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(rptPath).append("?");

        stringBuffer.append(paraStr).append("="+form.getRecordId());
        return CommonResult.success(stringBuffer);
    }

    /**
     * @description 列表查询数据
     **/
    @ApiOperation("获取其他费用新增下拉数据")
    @GetMapping("/getCheckData")
    public BaseResult getCheckData() {
        return reportsService.getCheckData();
    }

}
