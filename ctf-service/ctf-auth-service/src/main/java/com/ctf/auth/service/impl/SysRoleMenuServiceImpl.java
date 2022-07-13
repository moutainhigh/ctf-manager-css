package com.ctf.auth.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ctf.auth.model.po.SysMenu;
import com.ctf.auth.service.ISysMenuService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import com.ctf.common.utils.CurrentUserUtil;
import com.ctf.auth.model.po.SysRoleMenu;
import com.ctf.auth.mapper.SysRoleMenuMapper;
import com.ctf.auth.service.ISysRoleMenuService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 角色-菜单关联表 服务实现类
 *
 * @author jayud
 * @since 2022-02-21
 */
@Slf4j
@Service
public class SysRoleMenuServiceImpl extends ServiceImpl<SysRoleMenuMapper, SysRoleMenu> implements ISysRoleMenuService {


    @Autowired
    private SysRoleMenuMapper sysRoleMenuMapper;
    @Autowired
    private ISysMenuService sysMenuService;

    @Override
    public IPage<SysRoleMenu> selectPage(SysRoleMenu sysRoleMenu,
                                         Integer currentPage,
                                         Integer pageSize,
                                         HttpServletRequest req) {

        Page<SysRoleMenu> page = new Page<SysRoleMenu>(currentPage, pageSize);
        IPage<SysRoleMenu> pageList = sysRoleMenuMapper.pageList(page, sysRoleMenu);
        return pageList;
    }

    @Override
    public List<SysRoleMenu> selectList(SysRoleMenu sysRoleMenu) {
        return sysRoleMenuMapper.list(sysRoleMenu);
    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public void phyDelById(Long id) {
        sysRoleMenuMapper.phyDelById(id);
    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public void logicDel(Long id) {
        sysRoleMenuMapper.logicDel(id, CurrentUserUtil.getUsername());
    }

    @Override
    public void deleteByRoleId(Long roleId) {
        QueryWrapper<SysRoleMenu> condition = new QueryWrapper<>();
        condition.lambda().eq(SysRoleMenu::getIsDeleted, false).eq(SysRoleMenu::getRoleId, roleId);
        this.update(new SysRoleMenu().setIsDeleted(true), condition);
    }

    @Override
    public List<Long> getMenuIdsByRoleId(Long roleId) {
        List<SysRoleMenu> roleMenus = this.baseMapper.selectList(new QueryWrapper<>(new SysRoleMenu().setIsDeleted(false).setRoleId(roleId)));
        if (CollectionUtil.isEmpty(roleMenus)){
            return new ArrayList<>();
        }
        List<Long> menuIds = roleMenus.stream().map(e -> e.getMenuId()).collect(Collectors.toList());
        List<SysMenu> sysMenus = sysMenuService.listByIds(menuIds);
        Map<Long, Long> map = new HashMap<>();
        sysMenus.forEach(e -> {
            map.put(e.getParentId(), e.getParentId());
        });
        menuIds = menuIds.stream().filter(e -> !map.containsValue(e)).collect(Collectors.toList());
        return menuIds;
    }

    @Override
    public List<SysRoleMenu> getSystemRoleMenuByRoleIdsAndActionCode(Set<Long> longs, Long menuId) {

        QueryWrapper<SysRoleMenu> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().in(SysRoleMenu::getRoleId,longs);
        queryWrapper.lambda().eq(SysRoleMenu::getMenuId,menuId);
        queryWrapper.lambda().eq(SysRoleMenu::getIsDeleted,0);

        List<SysRoleMenu> systemRoleActionList = this.baseMapper.selectList(queryWrapper);
        return systemRoleActionList;
    }

    @Override
    public boolean deleteByRoleIdsAndMenuIds(List<Long> roleIds, List<Long> menuIds) {
        return this.baseMapper.deleteByRoleIdsAndMenuIds(roleIds,menuIds,CurrentUserUtil.getUsername());
    }

}
