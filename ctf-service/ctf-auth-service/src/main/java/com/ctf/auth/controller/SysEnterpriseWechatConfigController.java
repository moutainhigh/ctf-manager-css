package com.ctf.auth.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.ctf.common.constant.SysTips;
import com.ctf.common.BaseResult;
import com.ctf.auth.service.ISysEnterpriseWechatConfigService;
import com.ctf.auth.model.po.SysEnterpriseWechatConfig;

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
 * 系统企业微信配置表 控制类
 *
 * @author jayud
 * @since 2022-02-24
 */
@Slf4j
@Api(tags = "系统企业微信配置表")
@RestController
@RequestMapping("/sysEnterpriseWechatConfig")
public class SysEnterpriseWechatConfigController {



    @Autowired
    public ISysEnterpriseWechatConfigService sysEnterpriseWechatConfigService;


    /**
     * @description 分页查询
     * @author  jayud
     * @date   2022-02-24
     * @param: sysEnterpriseWechatConfig
     * @param: currentPage
     * @param: pageSize
     * @param: req
     * @return: com.ctf.common.BaseResult<com.baomidou.mybatisplus.core.metadata.IPage<com.ctf.auth.model.po.SysEnterpriseWechatConfig>>
     **/
    @ApiOperation("分页查询数据")
    @GetMapping("/selectPage")
    public BaseResult<IPage<SysEnterpriseWechatConfig>> selectPage(SysEnterpriseWechatConfig sysEnterpriseWechatConfig,
                                                   @RequestParam(name="currentPage", defaultValue="1") Integer currentPage,
                                                   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
                                                   HttpServletRequest req) {
        return BaseResult.ok(sysEnterpriseWechatConfigService.selectPage(sysEnterpriseWechatConfig,currentPage,pageSize,req));
    }


    /**
    * @description 列表查询数据
    * @author  jayud
    * @date   2022-02-24
    * @param: sysEnterpriseWechatConfig
    * @param: req
    * @return: com.ctf.common.BaseResult<java.util.List<com.ctf.auth.model.po.SysEnterpriseWechatConfig>>
    **/
    @ApiOperation("列表查询数据")
    @GetMapping("/selectList")
    public BaseResult<List<SysEnterpriseWechatConfig>> selectList(SysEnterpriseWechatConfig sysEnterpriseWechatConfig,
                                                HttpServletRequest req) {
        return BaseResult.ok(sysEnterpriseWechatConfigService.selectList(sysEnterpriseWechatConfig));
    }


    /**
    * @description 新增
    * @author  jayud
    * @date   2022-02-24
    * @param: sysEnterpriseWechatConfig
    * @return: com.ctf.common.BaseResult
    **/
    @ApiOperation("新增")
    @PostMapping("/add")
    public BaseResult add(@Valid @RequestBody SysEnterpriseWechatConfig sysEnterpriseWechatConfig ){
        sysEnterpriseWechatConfigService.save(sysEnterpriseWechatConfig);
        return BaseResult.ok(SysTips.ADD_SUCCESS);
    }


    /**
     * @description 编辑
     * @author  jayud
     * @date   2022-02-24
     * @param: sysEnterpriseWechatConfig
     * @return: com.ctf.common.BaseResult
     **/
    @ApiOperation("编辑")
    @PostMapping("/edit")
    public BaseResult edit(@Valid @RequestBody SysEnterpriseWechatConfig sysEnterpriseWechatConfig ){
        sysEnterpriseWechatConfigService.updateById(sysEnterpriseWechatConfig);
        return BaseResult.ok(SysTips.EDIT_SUCCESS);
    }



    /**
     * @description 物理删除
     * @author  jayud
     * @date   2022-02-24
     * @param: id
     * @return: com.ctf.common.BaseResult
     **/
    @ApiOperation("物理删除")
    @ApiImplicitParam(name = "id",value = "主键id",dataType = "Long",required = true)
    @GetMapping("/phyDel")
    public BaseResult phyDel(@RequestParam Long id){
        sysEnterpriseWechatConfigService.phyDelById(id);
        return BaseResult.ok(SysTips.DEL_SUCCESS);
    }

    /**
     * @description 逻辑删除
     * @author  jayud
     * @date   2022-02-24
     * @param: id
     * @return: com.ctf.common.BaseResult
     **/
    @ApiOperation("逻辑删除")
    @ApiImplicitParam(name = "id",value = "主键id",dataType = "Long",required = true)
    @GetMapping("/logicDel")
    public BaseResult logicDel(@RequestParam Long id){
        sysEnterpriseWechatConfigService.logicDel(id);
        return BaseResult.ok(SysTips.DEL_SUCCESS);
    }


    /**
     * @description 根据id查询
     * @author  jayud
     * @date   2022-02-24
     * @param: id
     * @return: com.ctf.common.BaseResult<com.ctf.auth.model.po.SysEnterpriseWechatConfig>
     **/
    @ApiOperation("根据id查询")
    @ApiImplicitParam(name = "id",value = "主键id",dataType = "int",required = true)
    @GetMapping(value = "/queryById")
    public BaseResult<SysEnterpriseWechatConfig> queryById(@RequestParam(name="id",required=true) int id) {
        SysEnterpriseWechatConfig sysEnterpriseWechatConfig = sysEnterpriseWechatConfigService.getById(id);
        return BaseResult.ok(sysEnterpriseWechatConfig);
    }

    /**
     * @description 保存数据
     * @author  ciro
     * @date   2022/2/24 13:02
     * @param: sysEmamilConfig
     * @return: com.ctf.common.BaseResult
     **/
    @ApiOperation("保存数据")
    @PostMapping("/saveConfig")
    public BaseResult saveConfig(@Valid @RequestBody SysEnterpriseWechatConfig sysEnterpriseWechatConfig ){
        return sysEnterpriseWechatConfigService.saveConfig(sysEnterpriseWechatConfig);
    }


    /**
     * @description 根据租户编码查询
     * @author  ciro
     * @date   2022/2/24 13:02
     * @param: tenantCode
     * @return: com.ctf.common.BaseResult<com.ctf.auth.model.po.SysEnterpriseWechatConfig>
     **/
    @ApiOperation("根据租户编码查询")
    @ApiImplicitParam(name = "tenantCode",value = "租户编码",dataType = "String",required = true)
    @GetMapping(value = "/selectByTenantCode")
    public BaseResult<SysEnterpriseWechatConfig> selectByTenantCode(@RequestParam(name="tenantCode",required=true) String tenantCode) {
        return sysEnterpriseWechatConfigService.selectByTenantCode(tenantCode);
    }


}
