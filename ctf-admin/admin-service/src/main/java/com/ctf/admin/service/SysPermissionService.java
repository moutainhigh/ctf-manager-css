package com.ctf.admin.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ctf.admin.pojo.entity.SysPermission;
import com.ctf.admin.pojo.query.PermPageQuery;
import com.ctf.admin.pojo.vo.perm.PermPageVO;

import java.util.List;

/**
 * 权限业务接口
 *
 * @author H.m
 * @date 2022/8/5 10:30
 */
public interface SysPermissionService extends IService<SysPermission> {

    /**
     * 权限<->有权限的角色集合
     *
     * @return
     */
    List<SysPermission> listPermRoles();

    /**
     * 根据角色编码集合获取按钮权限标识
     *
     * @param roles 角色权限编码集合
     * @return
     */
    List<String> listBtnPermByRoles(List<String> roles);

    /**
     * 刷新Redis缓存中角色菜单的权限规则，角色和菜单信息变更调用
     */
    boolean refreshPermRolesRules();

    /**
     * 获取权限分页列表
     *
     * @param permPageQuery
     * @return
     */
    IPage<PermPageVO> listPermPages(PermPageQuery permPageQuery);
}
