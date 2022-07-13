package com.ctf.auth.controller;

import com.ctf.auth.model.bo.QuerySysDeptForm;
import com.ctf.common.utils.CurrentUserUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.ctf.common.constant.SysTips;
import com.ctf.common.BaseResult;
import com.ctf.auth.service.ISysDepartService;
import com.ctf.auth.model.po.SysDepart;

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
 * 组织机构表 控制类
 *
 * @author jayud
 * @since 2022-02-22
 */
@Slf4j
@Api(tags = "组织机构表")
@RestController
@RequestMapping("/sysDepart")
public class SysDepartController {



    @Autowired
    public ISysDepartService sysDepartService;


    /**
     * @description 分页查询
     * @author  jayud
     * @date   2022-02-22
     * @param: sysDepart
     * @param: currentPage
     * @param: pageSize
     * @param: req
     * @return: com.ctf.common.BaseResult<com.baomidou.mybatisplus.core.metadata.IPage<com.ctf.auth.model.po.SysDepart>>
     **/
    @ApiOperation("分页查询数据")
    @PostMapping("/selectPage")
    public BaseResult<IPage<SysDepart>> selectPage(@RequestBody SysDepart sysDepart,
                                                   HttpServletRequest req) {
        return BaseResult.ok(sysDepartService.selectPage(sysDepart,sysDepart.getCurrentPage(),sysDepart.getPageSize(),req));
    }


    /**
    * @description 列表查询数据
    * @author  jayud
    * @date   2022-02-22
    * @param: sysDepart
    * @param: req
    * @return: com.ctf.common.BaseResult<java.util.List<com.ctf.auth.model.po.SysDepart>>
    **/
    @ApiOperation("列表查询数据")
    @GetMapping("/selectList")
    public BaseResult<List<SysDepart>> selectList(SysDepart sysDepart,
                                                HttpServletRequest req) {
        return BaseResult.ok(sysDepartService.selectList(sysDepart));
    }


    /**
    * @description 新增
    * @author  jayud
    * @date   2022-02-22
    * @param: sysDepart
    * @return: com.ctf.common.BaseResult
    **/
    @ApiOperation("新增")
    @PostMapping("/add")
    public BaseResult add(@Valid @RequestBody SysDepart sysDepart ){
        sysDepartService.save(sysDepart);
        return BaseResult.ok(SysTips.ADD_SUCCESS);
    }


    /**
     * @description 编辑
     * @author  jayud
     * @date   2022-02-22
     * @param: sysDepart
     * @return: com.ctf.common.BaseResult
     **/
    @ApiOperation("编辑")
    @PostMapping("/edit")
    public BaseResult edit(@Valid @RequestBody SysDepart sysDepart ){
        sysDepartService.updateById(sysDepart);
        return BaseResult.ok(SysTips.EDIT_SUCCESS);
    }



//    /**
//     * @description 物理删除
//     * @author  jayud
//     * @date   2022-02-22
//     * @param: id
//     * @return: com.ctf.common.BaseResult
//     **/
//    @ApiOperation("物理删除")
//    @ApiImplicitParam(name = "id",value = "主键id",dataType = "Long",required = true)
//    @GetMapping("/phyDel")
//    public BaseResult phyDel(@RequestParam Long id){
//        sysDepartService.phyDelById(id);
//        return BaseResult.ok(SysTips.DEL_SUCCESS);
//    }

    /**
     * @description 逻辑删除
     * @author  jayud
     * @date   2022-02-22
     * @param: id
     * @return: com.ctf.common.BaseResult
     **/
    @ApiOperation("逻辑删除")
    @ApiImplicitParam(name = "id",value = "主键id",dataType = "Long",required = true)
    @GetMapping("/logicDel")
    public BaseResult logicDel(@RequestParam Long id){
        sysDepartService.logicDel(id);
        return BaseResult.ok(SysTips.DEL_SUCCESS);
    }


    /**
     * @description 根据id查询
     * @author  jayud
     * @date   2022-02-22
     * @param: id
     * @return: com.ctf.common.BaseResult<com.ctf.auth.model.po.SysDepart>
     **/
    @ApiOperation("根据id查询")
    @ApiImplicitParam(name = "id",value = "主键id",dataType = "int",required = true)
    @GetMapping(value = "/queryById")
    public BaseResult<SysDepart> queryById(@RequestParam(name="id",required=true) int id) {
        SysDepart sysDepart = sysDepartService.queryById(id);
        return BaseResult.ok(sysDepart);
    }

    /**
     * 查询组织部门树
     * @param form
     * @return
     */
    @ApiOperation("查询组织部门树")
    @PostMapping("/selectDeptTree")
    public BaseResult<List<SysDepart>> selectDeptTree(@RequestBody QuerySysDeptForm form){
        List<SysDepart> deptTree = sysDepartService.selectDeptTree(form);
        return BaseResult.ok(deptTree);
    }

    /**
     * 保存组织or部门
     * @param depart
     * @return
     */
    @ApiOperation(value = "保存组织or部门")
    @PostMapping(value = "/save")
    public BaseResult saveSysDepart(@Valid @RequestBody SysDepart depart){
        SysDepart sysDepart = sysDepartService.getByOrgCode(depart.getOrgCode());
        if(sysDepart != null){
            if(null == depart.getId()){
                return BaseResult.error(444,"机构编码已存在");
            }
            if(null != depart.getId() && !depart.getId().equals(sysDepart.getId())){
                return BaseResult.error(444,"机构编码已存在");
            }
        }
        sysDepartService.saveSysDepart(depart);
        return BaseResult.ok("保存成功");
    }

    @ApiOperation("获取上级组织")
    @PostMapping("/selectSuperiorOrganization")
    public BaseResult<List<SysDepart>> selectSuperiorOrganization(@RequestBody QuerySysDeptForm form){
        List<SysDepart> deptTree = sysDepartService.selectSuperiorOrganization(form);
        return BaseResult.ok(deptTree);
    }

    @ApiOperation("获取接单主体")
    @PostMapping("/selectOperationSubject")
    public BaseResult<List<SysDepart>> selectOperationSubject(@RequestBody QuerySysDeptForm form){
        List<SysDepart> deptTree = sysDepartService.selectOperationSubject(form);
        return BaseResult.ok(deptTree);
    }


    /**
     * 查询组织部门树以及员工
     * @param form
     * @return
     */
    @ApiOperation("查询组织部门树以及员工")
    @PostMapping("/selectDeptTreeStaff")
    public BaseResult<List<SysDepart>> selectDeptTreeStaff(@RequestBody QuerySysDeptForm form){
        List<SysDepart> deptTree = sysDepartService.selectDeptTreeStaff(form);
        return BaseResult.ok(deptTree);
    }

    @ApiOperation("获取当前用户部门")
    @GetMapping("/selectUserDepart")
    public BaseResult selectUserDepart(){
        Long departId = CurrentUserUtil.getUserDetail().getDepartId();
        SysDepart byId = sysDepartService.getById(departId);
        return BaseResult.ok(byId);
    }

    /**
     * 查询操作部门
     * @description 查询操作部门
     **/
    @ApiOperation("查询操作部门")
    @RequestMapping("/getSysDepart")
    public BaseResult<List<SysDepart>> getSysDepart(){
        List<SysDepart> deptTree = sysDepartService.getSysDepart();
        return BaseResult.ok(deptTree);
    }

}
