package com.zdf.auth.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;


import com.zdf.common.utils.ExcelUtils;
import com.zdf.common.constant.SysTips;
import com.zdf.common.BaseResult;
import com.zdf.auth.service.IBBanksService;
import com.zdf.auth.model.po.BBanks;

import com.zdf.common.result.ListPageRuslt;
import com.zdf.common.result.PaginationBuilder;

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
 * 公司银行账户 控制类
 *
 * @author jayud
 * @since 2022-04-22
 */
@Slf4j
@Api(tags = "公司银行账户")
@RestController
@RequestMapping("/bBanks")
public class BBanksController {



    @Autowired
    public IBBanksService bBanksService;


    /**
     * @description 分页查询
     * @author  jayud
     * @date   2022-04-22
     * @param: bBanks
     * @param: currentPage
     * @param: pageSize
     * @param: req
     * @return: com.zdf.common.BaseResult<com.baomidou.mybatisplus.core.metadata.IPage<com.zdf.auth.model.po.BBanks>>
     **/
    @ApiOperation("分页查询数据")
    @GetMapping("/selectPage")
    public BaseResult<ListPageRuslt<BBanks>> selectPage(BBanks bBanks,
                                                   @RequestParam(name="currentPage", defaultValue="1") Integer currentPage,
                                                   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
                                                   HttpServletRequest req) {
        return BaseResult.ok(PaginationBuilder.buildPageResult(bBanksService.selectPage(bBanks,currentPage,pageSize,req)));
    }


    /**
    * @description 列表查询数据
    * @author  jayud
    * @date   2022-04-22
    * @param: bBanks
    * @param: req
    * @return: com.zdf.common.BaseResult<java.util.List<com.zdf.auth.model.po.BBanks>>
    **/
    @ApiOperation("列表查询数据")
    @GetMapping("/selectList")
    public BaseResult<List<BBanks>> selectList(BBanks bBanks) {
        return BaseResult.ok(bBanksService.selectList(bBanks));
    }

    @ApiOperation("列表查询数据")
    @PostMapping("/api/selectListFeign")
    public BaseResult<List<BBanks>> selectBankListFeign() {
        return BaseResult.ok(bBanksService.selectList(new BBanks()));
    }


    /**
    * @description 新增
    * @author  jayud
    * @date   2022-04-22
    * @param: bBanks
    * @return: com.zdf.common.BaseResult
    **/
    @ApiOperation("新增")
    @PostMapping("/add")
    public BaseResult add(@Valid @RequestBody BBanks bBanks ){
        bBanksService.save(bBanks);
        return BaseResult.ok(SysTips.ADD_SUCCESS);
    }


    /**
     * @description 编辑
     * @author  jayud
     * @date   2022-04-22
     * @param: bBanks
     * @return: com.zdf.common.BaseResult
     **/
    @ApiOperation("编辑")
    @PostMapping("/edit")
    public BaseResult edit(@Valid @RequestBody BBanks bBanks ){
        bBanksService.updateById(bBanks);
        return BaseResult.ok(SysTips.EDIT_SUCCESS);
    }



    /**
     * @description 物理删除
     * @author  jayud
     * @date   2022-04-22
     * @param: id
     * @return: com.zdf.common.BaseResult
     **/
    @ApiOperation("物理删除")
    @ApiImplicitParam(name = "id",value = "主键id",dataType = "Long",required = true)
    @GetMapping("/phyDel")
    public BaseResult phyDel(@RequestParam Long id){
        bBanksService.phyDelById(id);
        return BaseResult.ok(SysTips.DEL_SUCCESS);
    }

    /**
     * @description 逻辑删除
     * @author  jayud
     * @date   2022-04-22
     * @param: id
     * @return: com.zdf.common.BaseResult
     **/
    @ApiOperation("逻辑删除")
    @ApiImplicitParam(name = "id",value = "主键id",dataType = "Long",required = true)
    @GetMapping("/logicDel")
    public BaseResult logicDel(@RequestParam Long id){
        bBanksService.logicDel(id);
        return BaseResult.ok(SysTips.DEL_SUCCESS);
    }


    /**
     * @description 根据id查询
     * @author  jayud
     * @date   2022-04-22
     * @param: id
     * @return: com.zdf.common.BaseResult<com.zdf.auth.model.po.BBanks>
     **/
    @ApiOperation("根据id查询")
    @ApiImplicitParam(name = "id",value = "主键id",dataType = "int",required = true)
    @GetMapping(value = "/queryById")
    public BaseResult<BBanks> queryById(@RequestParam(name="id",required=true) int id) {
        BBanks bBanks = bBanksService.getById(id);
        return BaseResult.ok(bBanks);
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
    @ApiOperation("根据查询条件导出公司银行账户")
    @PostMapping(path = "/exportBBanks")
    public void exportBBanks(HttpServletResponse response, @RequestParam Map<String, Object> paramMap) {
        try {
            List<String> headList = Arrays.asList(
                "自动ID",
                "银行名称",
                "银行编号",
                "swift_code",
                "银行凭证代码（维度可核算项目）",
                "开户行支行名称",
                "银行地址",
                "联系人",
                "电话",
                "账户名称",
                "银行账号",
                "账户币别",
                "区域【境内 境外】",
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
            List<LinkedHashMap<String, Object>> dataList = bBanksService.queryBBanksForExcel(paramMap);
            ExcelUtils.exportExcel(headList, dataList, "公司银行账户", response);
        } catch (Exception e) {
            e.printStackTrace();
            log.warn(e.toString());
        }
    }


}
