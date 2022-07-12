package com.zdf.auth.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zdf.auth.model.bo.SysUserForm;
import com.zdf.auth.model.bo.SysUserToWarehouseForm;
import com.zdf.auth.model.po.SysRole;
import com.zdf.auth.model.po.SysUser;
import com.zdf.auth.model.po.SysUserToWarehouse;
import com.zdf.auth.model.vo.SysUserVO;
import com.zdf.auth.service.ISysUserService;
import com.zdf.common.BaseResult;
import com.zdf.common.utils.ListUtils;
import com.zdf.common.utils.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import com.zdf.common.utils.CurrentUserUtil;
import com.zdf.auth.model.po.SysUserRole;
import com.zdf.auth.mapper.SysUserRoleMapper;
import com.zdf.auth.service.ISysUserRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 用户-角色关联表 服务实现类
 *
 * @author jayud
 * @since 2022-02-22
 */
@Slf4j
@Service
public class SysUserRoleServiceImpl extends ServiceImpl<SysUserRoleMapper, SysUserRole> implements ISysUserRoleService {


    @Autowired
    private SysUserRoleMapper sysUserRoleMapper;
    @Autowired
    private ISysUserService sysUserService;

    @Override
    public IPage<SysUserRole> selectPage(SysUserRole sysUserRole,
                                         Integer currentPage,
                                         Integer pageSize,
                                         HttpServletRequest req) {

        Page<SysUserRole> page = new Page<SysUserRole>(currentPage, pageSize);
        IPage<SysUserRole> pageList = sysUserRoleMapper.pageList(page, sysUserRole);
        return pageList;
    }

    @Override
    public List<SysUserRole> selectList(SysUserRole sysUserRole) {
        return sysUserRoleMapper.list(sysUserRole);
    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public void phyDelById(Long id) {
        sysUserRoleMapper.phyDelById(id);
    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public void logicDel(Long id) {
        sysUserRoleMapper.logicDel(id, CurrentUserUtil.getUsername());
    }

    @Override
    public boolean exitByRolesIds(List<Long> rolesIds) {
        QueryWrapper<SysUserRole> condition = new QueryWrapper<>();
        condition.lambda().in(SysUserRole::getRoleId, rolesIds).eq(SysUserRole::getIsDeleted, false);
        return this.count(condition) > 0;
    }

    @Override
    public void associatedEmployees(Long rolesId, List<Long> userIds) {
        QueryWrapper<SysUserRole> condition = new QueryWrapper<>();
        condition.lambda().eq(SysUserRole::getRoleId, rolesId).eq(SysUserRole::getIsDeleted, false);
        this.update(new SysUserRole().setIsDeleted(true), condition);

        List<SysUserRole> list = new ArrayList<>();
        for (Long userId : userIds) {
            SysUserRole sysUserRole = new SysUserRole();
            sysUserRole.setRoleId(rolesId).setUserId(userId);
            list.add(sysUserRole);
        }
        this.saveBatch(list);
    }

    @Override
    public IPage<SysUserVO> selectAssociatedEmployeesPage(Long rolesId, Integer currentPage, Integer pageSize, HttpServletRequest req) {
        IPage<SysUserRole> userRolePage = this.selectPage(new SysUserRole().setRoleId(rolesId).setIsDeleted(false),
                currentPage, pageSize, req);
        List<SysUserRole> records = userRolePage.getRecords();
        List<Long> userIds = records.stream().map(e -> e.getUserId()).collect(Collectors.toList());
        if (userIds.size() == 0) {
            userIds.add(-1L);
        }
        IPage<SysUserVO> userPages = this.sysUserService.selectPage(new SysUserForm().setUserIds(userIds).setCode(CurrentUserUtil.getUserTenantCode()), currentPage, pageSize, req);
        return userPages;
    }

    @Override
    public void deleteEmployees(Long rolesId, List<Long> userIds) {
        QueryWrapper<SysUserRole> condition = new QueryWrapper<>();
        condition.lambda().eq(SysUserRole::getRoleId, rolesId).in(SysUserRole::getUserId, userIds)
                .eq(SysUserRole::getIsDeleted, false);

        this.update(new SysUserRole().setIsDeleted(true), condition);
    }

    @Override
    public List<SysUserRole> getByCondition(SysUserRole sysUserRole) {
        QueryWrapper<SysUserRole> condition = new QueryWrapper<>(sysUserRole);
        return this.baseMapper.selectList(condition);
    }

    @Override
    public void deleteByUserId(Long userId) {
        QueryWrapper<SysUserRole> condition = new QueryWrapper<>();
        condition.lambda().eq(SysUserRole::getUserId, userId)
                .eq(SysUserRole::getIsDeleted, false);
        this.baseMapper.update(new SysUserRole().setIsDeleted(true), condition);
    }

    @Override
    public List<Long> getUserIdsByRoleId(Long roleId) {
        List<SysUserRole> sysUserRoles = this.baseMapper.selectList(new QueryWrapper<>(new SysUserRole().setRoleId(roleId).setIsDeleted(false)));
        List<Long> userIds = sysUserRoles.stream().map(e -> e.getUserId()).collect(Collectors.toList());
        return userIds;
    }

    @Override
    public int getCountByUserNameAndRoleName(String username, String admin, String tenantCode) {
        return this.baseMapper.getCountByUserNameAndRoleName(username, admin, tenantCode);
    }

    @Override
    public int getCountByUserName(String username, String userTenantCode, String menuCode) {
        return this.baseMapper.getCountByUserName(username, userTenantCode, menuCode);
    }

    @Override
    public int getCountByUserNameAndStep(String username, String userTenantCode, String menuCode, Integer newStep) {
        return this.baseMapper.getCountByUserNameAndStep(username, userTenantCode, menuCode, newStep);
    }

    @Override
    public List<SysRole> getEnabledRolesByUserId(Long id) {
        return this.baseMapper.getEnabledRolesByUserId(id);
    }

    @Override
    public String getUserNameByRoles(Set<Long> roles) {
        if (CollectionUtil.isEmpty(roles)) {
            return "";
        }
        //查出这些角色关联的所有用户
        QueryWrapper<SysUserRole> queryWrapper = new QueryWrapper();
        queryWrapper.lambda().in(SysUserRole::getRoleId, roles);
        queryWrapper.lambda().eq(SysUserRole::getIsDeleted, 0);
        List<SysUserRole> sysUserRoles = this.list(queryWrapper);
        if(CollectionUtil.isEmpty(sysUserRoles)){
            return "";
        }
        Set<Long> userIds = new HashSet<>();
        for (SysUserRole sysUserRole : sysUserRoles) {
            userIds.add(sysUserRole.getUserId());
        }
        //查出用户
        List<SysUser> sysUsers = sysUserService.getUserByUserIds(userIds);
        if (CollectionUtil.isEmpty(sysUsers)) {
            return "";
        }
        StringBuffer stringBuffer = new StringBuffer();
        for (SysUser sysUser : sysUsers) {
            stringBuffer.append(sysUser.getUserName()).append(",");
        }
        return stringBuffer.substring(0, stringBuffer.length() - 1);
    }

    @Override
    public BaseResult saveUpdateSysUserRole(Long userId, List<Long> roleIds) {

        LambdaQueryWrapper<SysUserRole> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(SysUserRole::getIsDeleted, false);
        lambdaQueryWrapper.eq(SysUserRole::getUserId, userId);
        List<SysUserRole> SysUserRoleList = this.list(lambdaQueryWrapper);

        //上次选中的(之前的)
        List<String> collectAgo = SysUserRoleList.stream().map(x -> String.valueOf(x.getRoleId())).collect(Collectors.toList());

        // 本次(传进来当前的)
        List<String> collectPresent = roleIds.stream().map(x -> String.valueOf(x)).collect(Collectors.toList());

        //需要添加的
        List<String> add = ListUtils.getDiff(collectAgo, collectPresent);

        //需要删除的
        List<String> del = ListUtils.getDiff(collectPresent, collectAgo);


        if (CollUtil.isNotEmpty(add)) {
            List<SysUserRole> list = new ArrayList<>();

            List<Long> collect = add.stream().map(x -> Long.parseLong(x)).collect(Collectors.toList());

            collect.stream().forEach(x -> {
                SysUserRole sysUserRole = new SysUserRole();
                sysUserRole.setUserId(userId);
                sysUserRole.setRoleId(x);
                list.add(sysUserRole);
            });
            this.saveOrUpdateBatch(list);
        }

        if (CollUtil.isNotEmpty(del)) {
            List<Long> collect1 = del.stream().map(x -> Long.parseLong(x)).collect(Collectors.toList());
            String fileNameString = StringUtils.getFileNameString(collect1);
            //批量查询需要删除的数据
            LambdaQueryWrapper<SysUserRole> lambdaQueryWrapperdel = new LambdaQueryWrapper<>();
            lambdaQueryWrapperdel.in(SysUserRole::getRoleId, fileNameString);
            lambdaQueryWrapperdel.eq(SysUserRole::getIsDeleted, false);
            lambdaQueryWrapperdel.eq(SysUserRole::getUserId, userId);
            List<SysUserRole> sysUserRoleList = this.list(lambdaQueryWrapperdel);

            List<Long> collect = sysUserRoleList.stream().map(x -> x.getId()).collect(Collectors.toList());
            List<String> idStringList = collect.stream().map(x -> String.valueOf(x)).collect(Collectors.toList());
            sysUserRoleMapper.logicDelByIds(idStringList, CurrentUserUtil.getUsername());
        }
        return BaseResult.ok();
    }


}
