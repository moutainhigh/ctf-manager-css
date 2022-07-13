package com.ctf.auth.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ctf.auth.model.po.SysMenu;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import javax.annotation.ManagedBean;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * <p>
 * 系统菜单表 Mapper 接口
 * </p>
 *
 * @author jayud.dev
 * @since 2022-02-21
 */
@Mapper
public interface SysMenuMapper extends BaseMapper<SysMenu> {

    /**
     * 根据角色Ids，查询菜单
     * @param roleIds
     * @return
     */
    List<SysMenu> selectSysMenuByRoleIds(@Param("roleIds") List<Long> roleIds);

    /**
     * 获取所有菜单树
     * @return
     */
    List<SysMenu> allMenuTree(@Param("sysMenu") SysMenu sysMenu);

    /**
     * @description 根据菜单编码集合查询菜单
     * @author  ciro
     * @date   2022/2/23 15:40
     * @param: menuCodeList
     * @return: java.util.List<com.ctf.auth.model.po.SysMenu>
     **/
    List<SysMenu> selectSysMenuByMenuCodes(@Param("menuCodeList") List<String> menuCodeList);

    /**
     * 分页查询菜单
     * @param page
     * @param sysMenu
     * @return
     */
    IPage<SysMenu> pageList(@Param("page") Page<SysMenu> page, @Param("sysMenu") SysMenu sysMenu);

    /**
     * 导出菜单
     * @param sysMenu
     * @return
     */
    List<LinkedHashMap<String, Object>> exportSysMenu(@Param("sysMenu") SysMenu sysMenu);

    /**
     * 根据菜单id，查询菜单的所有子集菜单(递归)
     * @param menuId
     * @return
     */
    List<SysMenu> selectMenuChildren(@Param("menuId") Long menuId);

    /**
     * 根据角色ids，获取菜单及其按钮
     * @param roleIds
     * @return
     */
    List<SysMenu> selectSysMenuBtnByRoleIds(@Param("roleIds") List<Long> roleIds);

    /**
     * 获取所有是审核的菜单树
     * @return
     */
    List<SysMenu> allCheckMenuTree();

    /**
     * 获取所有菜单
     */
    List<SysMenu> allMenu();

    /**
     * 获取系统下所有菜单
     * @param delIdList 系统ids集合
     * @return
     */
    List<Long> getAllMenuBySysType(@Param("delIdList") List<String> delIdList);
}
