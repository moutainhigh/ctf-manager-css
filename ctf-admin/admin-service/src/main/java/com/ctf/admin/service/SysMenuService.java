package com.ctf.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ctf.admin.pojo.entity.SysMenu;
import com.ctf.admin.pojo.vo.menu.ResourceVO;
import com.ctf.admin.pojo.vo.menu.MenuVO;
import com.ctf.admin.pojo.vo.menu.RouteVO;
import com.ctf.common.web.domain.Option;

import java.util.List;

/**
 * 菜单业务接口
 *
 * @author H.m
 * @date 2022/8/5 10:30
 */
public interface SysMenuService extends IService<SysMenu> {

    /**
     * 获取菜单表格列表
     *
     * @param name 菜单名称
     * @return
     */
    List<MenuVO> listMenus(String name);


    /**
     * 获取菜单下拉列表
     *
     * @return
     */
    List<Option> listMenuOptions();

    /**
     * 新增菜单
     *
     * @param menu
     * @return
     */
    boolean saveMenu(SysMenu menu);

    /**
     * 清理路由缓存
     */
    void cleanCache();

    /**
     * 获取路由列表
     *
     * @return
     */
    List<RouteVO> listRoutes();

    /**
     * 资源(菜单+权限)树形列表
     *
     * @return
     */
    List<ResourceVO> listResources();

    /**
     * 修改菜单显示状态
     *
     * @param menuId 菜单ID
     * @param visible 是否显示(1->显示；2->隐藏)
     * @return
     */
    boolean updateMenuVisible(Long menuId, Integer visible);
}
