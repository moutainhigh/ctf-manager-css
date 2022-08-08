package com.ctf.admin.service;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ctf.admin.pojo.entity.SysRole;
import com.ctf.admin.pojo.form.RoleForm;
import com.ctf.admin.pojo.form.RoleResourceForm;
import com.ctf.admin.pojo.query.RolePageQuery;
import com.ctf.admin.pojo.vo.role.RolePageVO;
import com.ctf.common.web.domain.Option;

import java.util.List;

/**
 * 角色业务接口层
 *
 * @author H.m
 * @date 2022/8/5 10:30
 */
public interface SysRoleService extends IService<SysRole> {

    /**
     * 角色分页列表
     *
     * @param queryParams
     * @return
     */
    Page<RolePageVO> listRolePages(RolePageQuery queryParams);


    /**
     * 角色下拉列表
     *
     * @return
     */
    List<Option> listRoleOptions();

    /**
     *
     * @param roleForm
     * @return
     */
    boolean saveRole(RoleForm roleForm);

    /**
     * 修改角色状态
     *
     * @param roleId
     * @param status
     * @return
     */
    boolean updateRoleStatus(Long roleId, Integer status);

    /**
     * 批量删除角色
     *
     * @param ids
     * @return
     */
    boolean deleteRoles(String ids);


    /**
     * 获取角色的资源ID集合,资源包括菜单和权限
     *
     * @param roleId
     * @return
     */
    RoleResourceForm getRoleResources(Long roleId);


    /**
     * 修改角色的资源权限
     *
     * @param roleId
     * @param roleResourceForm
     * @return
     */
    boolean updateRoleResource(Long roleId, RoleResourceForm roleResourceForm);
}