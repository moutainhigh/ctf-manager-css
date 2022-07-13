package com.ctf.auth.service;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.ctf.auth.model.po.SysRoleMenu;
import com.baomidou.mybatisplus.extension.service.IService;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * 角色-菜单关联表 服务类
 *
 * @author jayud
 * @since 2022-02-21
 */
public interface ISysRoleMenuService extends IService<SysRoleMenu> {


    /**
     * @description 分页查询
     * @author  jayud
     * @date   2022-02-21
     * @param: sysRoleMenu
     * @param: currentPage
     * @param: pageSize
     * @param: req
     * @return: com.baomidou.mybatisplus.core.metadata.IPage<com.ctf.auth.model.po.SysRoleMenu>
     **/
    IPage<SysRoleMenu> selectPage(SysRoleMenu sysRoleMenu,
                                Integer currentPage,
                                Integer pageSize,
                                HttpServletRequest req);

    /**
     * @description 列表查询数据
     * @author  jayud
     * @date   2022-02-21
     * @param: sysRoleMenu
     * @param: req
     * @return: java.util.List<com.ctf.auth.model.po.SysRoleMenu>
     **/
    List<SysRoleMenu> selectList(SysRoleMenu sysRoleMenu);



    /**
     * @description 物理删除
     * @author  jayud
     * @date   2022-02-21
     * @param: id
     * @return: void
     **/
    void phyDelById(Long id);


    /**
    * @description 逻辑删除
    * @author  jayud
    * @date   2022-02-21
    * @param: id
    * @return: com.ctf.component.commons.result.Result
    **/
    void logicDel(Long id);


    void deleteByRoleId(Long roleId);

    List<Long> getMenuIdsByRoleId(Long roleId);

    /**
     * 获取按钮与角色关系
     * @param longs
     * @param  menuId
     * @return
     */
    List<SysRoleMenu> getSystemRoleMenuByRoleIdsAndActionCode(Set<Long> longs, Long menuId);

    /**
     * 删除关联关系
     * @param roleIds 角色id集合
     * @param menuIds 菜单id集合
     * @return
     */
    boolean deleteByRoleIdsAndMenuIds(List<Long> roleIds, List<Long> menuIds);
}
