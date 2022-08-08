package com.ctf.admin.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.ctf.admin.pojo.entity.SysRolePermission;

import java.util.List;

/**
 * 角色权限业务接口
 *
 * @author H.m
 * @date 2022/8/5 10:30
 */
public interface SysRolePermissionService extends IService<SysRolePermission> {

    /**
     * 获取角色拥有的权限ID集合
     *
     * @param roleId
     * @return
     */
    List<Long> listPermIdsByRoleId(Long roleId);
}
