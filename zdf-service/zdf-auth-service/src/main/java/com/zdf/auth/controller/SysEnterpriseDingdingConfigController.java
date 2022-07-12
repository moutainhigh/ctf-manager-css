package com.zdf.auth.controller;

import com.zdf.auth.model.po.SysEmamilConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zdf.common.constant.SysTips;
import com.zdf.common.BaseResult;
import com.zdf.auth.service.ISysEnterpriseDingdingConfigService;
import com.zdf.auth.model.po.SysEnterpriseDingdingConfig;

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
 * 系统钉钉配置表 控制类
 *
 * @author jayud
 * @since 2022-02-24
 */
@Slf4j
@Api(tags = "系统钉钉配置表")
@RestController
@RequestMapping("/sysEnterpriseDingdingConfig")
public class SysEnterpriseDingdingConfigController {



    @Autowired
    public ISysEnterpriseDingdingConfigService sysEnterpriseDingdingConfigService;


    /**
     * @description 分页查询
     * @author  jayud
     * @date   2022-02-24
     * @param: sysEnterpriseDingdingConfig
     * @param: currentPage
     * @param: pageSize
     * @param: req
     * @return: com.zdf.common.BaseResult<com.baomidou.mybatisplus.core.metadata.IPage<com.zdf.auth.model.po.SysEnterpriseDingdingConfig>>
     **/
    @ApiOperation("分页查询数据")
    @GetMapping("/selectPage")
    public BaseResult<IPage<SysEnterpriseDingdingConfig>> selectPage(SysEnterpriseDingdingConfig sysEnterpriseDingdingConfig,
                                                   @RequestParam(name="currentPage", defaultValue="1") Integer currentPage,
                                                   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
                                                   HttpServletRequest req) {
        return BaseResult.ok(sysEnterpriseDingdingConfigService.selectPage(sysEnterpriseDingdingConfig,currentPage,pageSize,req));
    }


    /**
    * @description 列表查询数据
    * @author  jayud
    * @date   2022-02-24
    * @param: sysEnterpriseDingdingConfig
    * @param: req
    * @return: com.zdf.common.BaseResult<java.util.List<com.zdf.auth.model.po.SysEnterpriseDingdingConfig>>
    **/
    @ApiOperation("列表查询数据")
    @GetMapping("/selectList")
    public BaseResult<List<SysEnterpriseDingdingConfig>> selectList(SysEnterpriseDingdingConfig sysEnterpriseDingdingConfig,
                                                HttpServletRequest req) {
        return BaseResult.ok(sysEnterpriseDingdingConfigService.selectList(sysEnterpriseDingdingConfig));
    }


    /**
    * @description 新增
    * @author  jayud
    * @date   2022-02-24
    * @param: sysEnterpriseDingdingConfig
    * @return: com.zdf.common.BaseResult
    **/
    @ApiOperation("新增")
    @PostMapping("/add")
    public BaseResult add(@Valid @RequestBody SysEnterpriseDingdingConfig sysEnterpriseDingdingConfig ){
        sysEnterpriseDingdingConfigService.save(sysEnterpriseDingdingConfig);
        return BaseResult.ok(SysTips.ADD_SUCCESS);
    }


    /**
     * @description 编辑
     * @author  jayud
     * @date   2022-02-24
     * @param: sysEnterpriseDingdingConfig
     * @return: com.zdf.common.BaseResult
     **/
    @ApiOperation("编辑")
    @PostMapping("/edit")
    public BaseResult edit(@Valid @RequestBody SysEnterpriseDingdingConfig sysEnterpriseDingdingConfig ){
        sysEnterpriseDingdingConfigService.updateById(sysEnterpriseDingdingConfig);
        return BaseResult.ok(SysTips.EDIT_SUCCESS);
    }



    /**
     * @description 物理删除
     * @author  jayud
     * @date   2022-02-24
     * @param: id
     * @return: com.zdf.common.BaseResult
     **/
    @ApiOperation("物理删除")
    @ApiImplicitParam(name = "id",value = "主键id",dataType = "Long",required = true)
    @GetMapping("/phyDel")
    public BaseResult phyDel(@RequestParam Long id){
        sysEnterpriseDingdingConfigService.phyDelById(id);
        return BaseResult.ok(SysTips.DEL_SUCCESS);
    }

    /**
     * @description 逻辑删除
     * @author  jayud
     * @date   2022-02-24
     * @param: id
     * @return: com.zdf.common.BaseResult
     **/
    @ApiOperation("逻辑删除")
    @ApiImplicitParam(name = "id",value = "主键id",dataType = "Long",required = true)
    @GetMapping("/logicDel")
    public BaseResult logicDel(@RequestParam Long id){
        sysEnterpriseDingdingConfigService.logicDel(id);
        return BaseResult.ok(SysTips.DEL_SUCCESS);
    }


    /**
     * @description 根据id查询
     * @author  jayud
     * @date   2022-02-24
     * @param: id
     * @return: com.zdf.common.BaseResult<com.zdf.auth.model.po.SysEnterpriseDingdingConfig>
     **/
    @ApiOperation("根据id查询")
    @ApiImplicitParam(name = "id",value = "主键id",dataType = "int",required = true)
    @GetMapping(value = "/queryById")
    public BaseResult<SysEnterpriseDingdingConfig> queryById(@RequestParam(name="id",required=true) int id) {
        SysEnterpriseDingdingConfig sysEnterpriseDingdingConfig = sysEnterpriseDingdingConfigService.getById(id);
        return BaseResult.ok(sysEnterpriseDingdingConfig);
    }

    /**
     * @description 保存数据
     * @author  ciro
     * @date   2022/2/24 13:02
     * @param: sysEmamilConfig
     * @return: com.zdf.common.BaseResult
     **/
    @ApiOperation("保存数据")
    @PostMapping("/saveConfig")
    public BaseResult saveConfig(@Valid @RequestBody SysEnterpriseDingdingConfig sysEnterpriseDingdingConfig ){
        return sysEnterpriseDingdingConfigService.saveConfig(sysEnterpriseDingdingConfig);
    }


    /**
     * @description 根据租户编码查询
     * @author  ciro
     * @date   2022/2/24 13:02
     * @param: tenantCode
     * @return: com.zdf.common.BaseResult<com.zdf.auth.model.po.SysEnterpriseDingdingConfig>
     **/
    @ApiOperation("根据租户编码查询")
    @ApiImplicitParam(name = "tenantCode",value = "租户编码",dataType = "String",required = true)
    @GetMapping(value = "/selectByTenantCode")
    public BaseResult<SysEnterpriseDingdingConfig> selectByTenantCode(@RequestParam(name="tenantCode",required=true) String tenantCode) {
        return sysEnterpriseDingdingConfigService.selectByTenantCode(tenantCode);
    }


}
