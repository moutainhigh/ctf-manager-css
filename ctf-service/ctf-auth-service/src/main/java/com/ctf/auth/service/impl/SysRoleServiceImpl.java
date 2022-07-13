package com.ctf.auth.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ctf.auth.model.dto.AddSysRole;
import com.ctf.auth.model.po.*;
import com.ctf.auth.model.vo.SysRoleVO;
import com.ctf.auth.service.*;
import com.ctf.common.BaseResult;
import com.ctf.common.exception.JayudBizException;
import com.ctf.common.utils.ConvertUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.beans.factory.annotation.Autowired;
import com.ctf.common.utils.CurrentUserUtil;
import com.ctf.auth.mapper.SysRoleMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 角色表 服务实现类
 *
 * @author jayud
 * @since 2022-02-21
 */
@Slf4j
@Service
public class SysRoleServiceImpl extends ServiceImpl<SysRoleMapper, SysRole> implements ISysRoleService {


    @Autowired
    private SysRoleMapper sysRoleMapper;
    @Autowired
    private ISysUserRoleService sysUserRoleService;
    @Autowired
    private ISysRoleMenuService sysRoleMenuService;

    @Autowired
    private ISysMenuService sysMenuService;

    @Autowired
    private ISysRoleActionDataService sysRoleActionDataService;

    @Override
    public IPage<SysRoleVO> selectPage(SysRole sysRole,
                                       Integer currentPage,
                                       Integer pageSize,
                                       HttpServletRequest req) {
        Page<SysRole> page = new Page<SysRole>(currentPage, pageSize);
        IPage<SysRoleVO> pageList = sysRoleMapper.pageList(page, sysRole);
        return pageList;
    }

    @Override
    public List<SysRole> selectList(SysRole sysRole) {
        return sysRoleMapper.list(sysRole);
    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public void phyDelById(Long id) {
        sysRoleMapper.phyDelById(id);
    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public void logicDel(Long id) {
        sysRoleMapper.logicDel(id, CurrentUserUtil.getUsername());
    }

    @Override
    public List<SysRole> selectSysRoleByUserId(Long userId) {
        return sysRoleMapper.selectSysRoleByUserId(userId);
    }

    @Override
    public boolean checkUnique(Long id, String roleName, String roleCode) {
        SysRole sysRole = this.getOne(new QueryWrapper<>(new SysRole().setIsDeleted(false).setRoleName(roleName).setCode(CurrentUserUtil.getUserTenantCode())));
        if (sysRole != null && !sysRole.getId().equals(id)) {
            throw new JayudBizException("角色名称必须唯一");
        }
        sysRole = this.getOne(new QueryWrapper<>(new SysRole().setIsDeleted(false).setRoleCode(roleCode).setCode(CurrentUserUtil.getUserTenantCode())));
        if (sysRole != null && !sysRole.getId().equals(id)) {
            throw new JayudBizException("角色编码必须唯一");
        }
        return true;
    }

    @Override
    public void addOrUpdate(AddSysRole form) {
        SysRole sysRole = ConvertUtil.convert(form, SysRole.class);
        if (sysRole.getId() == null) {
            sysRole.setCreateTime(new Date()).setCreateBy(CurrentUserUtil.getUsername());
            sysRole.setCode(CurrentUserUtil.getUserTenantCode());
            this.save(sysRole);

            //新增角色成功，新增所有菜单的权限初始化
            //获取所有子菜单
            QueryWrapper<SysMenu> queryWrapper = new QueryWrapper();
            queryWrapper.lambda().eq(SysMenu::getIsDeleted,0);
            queryWrapper.lambda().ne(SysMenu::getParentId,0);
            queryWrapper.lambda().eq(SysMenu::getIsButton,false);
            List<SysMenu> list = sysMenuService.list(queryWrapper);
            List<SysRoleActionData> sysRoleActionDataList = new ArrayList<>();
            for (SysMenu sysMenu : list) {
                SysRoleActionData sysRoleActionData = new SysRoleActionData();
                sysRoleActionData.setCrtBy(CurrentUserUtil.getUserDetail().getId().intValue());
                sysRoleActionData.setCrtByDtm(LocalDateTime.now());
                sysRoleActionData.setCrtByName(CurrentUserUtil.getUsername());
                sysRoleActionData.setActionId(sysMenu.getId().intValue());
                sysRoleActionData.setActionCode(sysMenu.getCode());
                sysRoleActionData.setDateType(1);
                sysRoleActionData.setRoleId(sysRole.getId().intValue());
                sysRoleActionDataList.add(sysRoleActionData);
            }
            boolean result = this.sysRoleActionDataService.saveBatch(sysRoleActionDataList);
            if(result){
                log.warn("数据权限初始化成功");
            }
        } else {
            sysRole.setUpdateTime(new Date()).setUpdateBy(CurrentUserUtil.getUsername());
            this.updateById(sysRole);
        }
    }

    @Override
    public List<Long> getRoleIdsByUserId(Long userId) {
        List<SysUserRole> tmps = this.sysUserRoleService.getByCondition(new SysUserRole().setUserId(userId).setIsDeleted(false));
        List<Long> roleIds = tmps.stream().map(e -> e.getRoleId()).collect(Collectors.toList());
        return roleIds;
    }

    @Override
    public void setRoles(Long userId, List<Long> roleIds) {
        this.sysUserRoleService.deleteByUserId(userId);
        List<SysUserRole> list = new ArrayList<>();
        for (Long roleId : roleIds) {
            SysUserRole tmp = new SysUserRole();
            tmp.setRoleId(roleId).setUserId(userId);
            list.add(tmp);
        }
        this.sysUserRoleService.saveBatch(list);
    }

    @Override
    public void setRolePermissions(Long roleId, List<Long> menuIds) {
        this.sysRoleMenuService.deleteByRoleId(roleId);

        List<SysRoleMenu> list = new ArrayList<>();
        for (Long menuId : menuIds) {
            SysRoleMenu tmp = new SysRoleMenu();
            tmp.setMenuId(menuId).setRoleId(roleId);
            list.add(tmp);
        }
        this.sysRoleMenuService.saveBatch(list);
    }

    @Override
    public List<SysRole> selectRoleByUsername(String username) {
        return sysRoleMapper.selectRoleByUsername(username);
    }

    @Override
    public List<SysRole> getRoleByTenantCode(String tenantCode) {
        LambdaQueryWrapper<SysRole> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(SysRole::getIsDeleted,false);
        lambdaQueryWrapper.eq(SysRole::getCode,tenantCode);
        List<SysRole> roleList = this.list(lambdaQueryWrapper);
        return roleList;
    }

}
