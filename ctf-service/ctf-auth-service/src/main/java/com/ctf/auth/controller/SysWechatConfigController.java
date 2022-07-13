package com.ctf.auth.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.ctf.common.constant.SysTips;
import com.ctf.common.BaseResult;
import com.ctf.auth.service.ISysWechatConfigService;
import com.ctf.auth.model.po.SysWechatConfig;

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
 * 系统微信配置表 控制类
 *
 * @author jayud
 * @since 2022-02-24
 */
@Slf4j
@Api(tags = "系统微信配置表")
@RestController
@RequestMapping("/sysWechatConfig")
public class SysWechatConfigController {



    @Autowired
    public ISysWechatConfigService sysWechatConfigService;


    /**
     * @description 分页查询
     * @author  jayud
     * @date   2022-02-24
     * @param: sysWechatConfig
     * @param: currentPage
     * @param: pageSize
     * @param: req
     * @return: com.ctf.common.BaseResult<com.baomidou.mybatisplus.core.metadata.IPage<com.ctf.auth.model.po.SysWechatConfig>>
     **/
    @ApiOperation("分页查询数据")
    @GetMapping("/selectPage")
    public BaseResult<IPage<SysWechatConfig>> selectPage(SysWechatConfig sysWechatConfig,
                                                   @RequestParam(name="currentPage", defaultValue="1") Integer currentPage,
                                                   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
                                                   HttpServletRequest req) {
        return BaseResult.ok(sysWechatConfigService.selectPage(sysWechatConfig,currentPage,pageSize,req));
    }


    /**
    * @description 列表查询数据
    * @author  jayud
    * @date   2022-02-24
    * @param: sysWechatConfig
    * @param: req
    * @return: com.ctf.common.BaseResult<java.util.List<com.ctf.auth.model.po.SysWechatConfig>>
    **/
    @ApiOperation("列表查询数据")
    @GetMapping("/selectList")
    public BaseResult<List<SysWechatConfig>> selectList(SysWechatConfig sysWechatConfig,
                                                HttpServletRequest req) {
        return BaseResult.ok(sysWechatConfigService.selectList(sysWechatConfig));
    }


    /**
    * @description 新增
    * @author  jayud
    * @date   2022-02-24
    * @param: sysWechatConfig
    * @return: com.ctf.common.BaseResult
    **/
    @ApiOperation("新增")
    @PostMapping("/add")
    public BaseResult add(@Valid @RequestBody SysWechatConfig sysWechatConfig ){
        sysWechatConfigService.save(sysWechatConfig);
        return BaseResult.ok(SysTips.ADD_SUCCESS);
    }


    /**
     * @description 编辑
     * @author  jayud
     * @date   2022-02-24
     * @param: sysWechatConfig
     * @return: com.ctf.common.BaseResult
     **/
    @ApiOperation("编辑")
    @PostMapping("/edit")
    public BaseResult edit(@Valid @RequestBody SysWechatConfig sysWechatConfig ){
        sysWechatConfigService.updateById(sysWechatConfig);
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
        sysWechatConfigService.phyDelById(id);
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
        sysWechatConfigService.logicDel(id);
        return BaseResult.ok(SysTips.DEL_SUCCESS);
    }


    /**
     * @description 根据id查询
     * @author  jayud
     * @date   2022-02-24
     * @param: id
     * @return: com.ctf.common.BaseResult<com.ctf.auth.model.po.SysWechatConfig>
     **/
    @ApiOperation("根据id查询")
    @ApiImplicitParam(name = "id",value = "主键id",dataType = "int",required = true)
    @GetMapping(value = "/queryById")
    public BaseResult<SysWechatConfig> queryById(@RequestParam(name="id",required=true) int id) {
        SysWechatConfig sysWechatConfig = sysWechatConfigService.getById(id);
        return BaseResult.ok(sysWechatConfig);
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
    public BaseResult saveConfig(@Valid @RequestBody SysWechatConfig sysWechatConfig ){
        return sysWechatConfigService.saveConfig(sysWechatConfig);
    }


    /**
     * @description 根据租户编码查询
     * @author  ciro
     * @date   2022/2/24 13:02
     * @param: tenantCode
     * @return: com.ctf.common.BaseResult<com.ctf.auth.model.po.SysWechatConfig>
     **/
    @ApiOperation("根据租户编码查询")
    @ApiImplicitParam(name = "tenantCode",value = "租户编码",dataType = "String",required = true)
    @GetMapping(value = "/selectByTenantCode")
    public BaseResult<SysWechatConfig> selectByTenantCode(@RequestParam(name="tenantCode",required=true) String tenantCode) {
        return sysWechatConfigService.selectByTenantCode(tenantCode);
    }


}
