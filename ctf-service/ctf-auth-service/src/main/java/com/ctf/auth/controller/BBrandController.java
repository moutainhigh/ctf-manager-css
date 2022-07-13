package com.ctf.auth.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;


import com.ctf.common.utils.ExcelUtils;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.ctf.common.constant.SysTips;
import com.ctf.common.BaseResult;
import com.ctf.auth.service.IBBrandService;
import com.ctf.auth.model.po.BBrand;

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
 * 品牌库表 控制类
 *
 * @author jayud
 * @since 2022-04-22
 */
@Slf4j
@Api(tags = "品牌库表")
@RestController
@RequestMapping("/bBrand")
public class BBrandController {



    @Autowired
    public IBBrandService bBrandService;


    /**
     * @description 分页查询
     * @author  jayud
     * @date   2022-04-22
     * @param: bBrand
     * @param: currentPage
     * @param: pageSize
     * @param: req
     * @return: com.ctf.common.BaseResult<com.baomidou.mybatisplus.core.metadata.IPage<com.ctf.auth.model.po.BBrand>>
     **/
    @ApiOperation("分页查询数据")
    @GetMapping("/selectPage")
    public BaseResult<ListPageRuslt<BBrand>> selectPage(BBrand bBrand,
                                                   @RequestParam(name="currentPage", defaultValue="1") Integer currentPage,
                                                   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
                                                   HttpServletRequest req) {
        return BaseResult.ok(PaginationBuilder.buildPageResult(bBrandService.selectPage(bBrand,currentPage,pageSize,req)));
    }


    /**
    * @description 列表查询数据
    * @author  jayud
    * @date   2022-04-22
    * @param: bBrand
    * @param: req
    * @return: com.ctf.common.BaseResult<java.util.List<com.ctf.auth.model.po.BBrand>>
    **/
    @ApiOperation("列表查询数据")
    @GetMapping("/selectList")
    public BaseResult<List<BBrand>> selectList(BBrand bBrand,
                                                HttpServletRequest req) {
        return BaseResult.ok(bBrandService.selectList(bBrand));
    }


    /**
    * @description 新增
    * @author  jayud
    * @date   2022-04-22
    * @param: bBrand
    * @return: com.ctf.common.BaseResult
    **/
    @ApiOperation("新增")
    @PostMapping("/add")
    public BaseResult add(@Valid @RequestBody BBrand bBrand ){
        bBrandService.save(bBrand);
        return BaseResult.ok(SysTips.ADD_SUCCESS);
    }


    /**
     * @description 编辑
     * @author  jayud
     * @date   2022-04-22
     * @param: bBrand
     * @return: com.ctf.common.BaseResult
     **/
    @ApiOperation("编辑")
    @PostMapping("/edit")
    public BaseResult edit(@Valid @RequestBody BBrand bBrand ){
        bBrandService.updateById(bBrand);
        return BaseResult.ok(SysTips.EDIT_SUCCESS);
    }



    /**
     * @description 物理删除
     * @author  jayud
     * @date   2022-04-22
     * @param: id
     * @return: com.ctf.common.BaseResult
     **/
    @ApiOperation("物理删除")
    @ApiImplicitParam(name = "id",value = "主键id",dataType = "Long",required = true)
    @GetMapping("/phyDel")
    public BaseResult phyDel(@RequestParam Long id){
        bBrandService.phyDelById(id);
        return BaseResult.ok(SysTips.DEL_SUCCESS);
    }

    /**
     * @description 逻辑删除
     * @author  jayud
     * @date   2022-04-22
     * @param: id
     * @return: com.ctf.common.BaseResult
     **/
    @ApiOperation("逻辑删除")
    @ApiImplicitParam(name = "id",value = "主键id",dataType = "Long",required = true)
    @GetMapping("/logicDel")
    public BaseResult logicDel(@RequestParam Long id){
        bBrandService.logicDel(id);
        return BaseResult.ok(SysTips.DEL_SUCCESS);
    }


    /**
     * @description 根据id查询
     * @author  jayud
     * @date   2022-04-22
     * @param: id
     * @return: com.ctf.common.BaseResult<com.ctf.auth.model.po.BBrand>
     **/
    @ApiOperation("根据id查询")
    @ApiImplicitParam(name = "id",value = "主键id",dataType = "int",required = true)
    @GetMapping(value = "/queryById")
    public BaseResult<BBrand> queryById(@RequestParam(name="id",required=true) int id) {
        BBrand bBrand = bBrandService.getById(id);
        return BaseResult.ok(bBrand);
    }


    /**
    * @description 根据查询条件导出收货单
    * @author  jayud
    * @date   2022-04-22
    * @param: response  响应对象
    * @param: queryReceiptForm  参数queryReceiptForm
    * @param: req
    * @return: void
    **/
    @ApiOperation("根据查询条件导出品牌库表")
    @PostMapping(path = "/exportBBrand")
    public void exportBBrand(HttpServletResponse response, @RequestParam Map<String, Object> paramMap) {
        try {
            List<String> headList = Arrays.asList(
                "自动ID",
                "英文名称",
                "中文名称",
                "显示名称",
                "备注",
                "创建人ID",
                "创建人名称",
                "创建时间",
                "最后修改人ID",
                "最后修改人名称",
                "最后修改时间",
                "删除标记",
                "删除人ID",
                "删除人名称",
                "删除时间"
            );
            List<LinkedHashMap<String, Object>> dataList = bBrandService.queryBBrandForExcel(paramMap);
            ExcelUtils.exportExcel(headList, dataList, "品牌库表", response);
        } catch (Exception e) {
            e.printStackTrace();
            log.warn(e.toString());
        }
    }


}
