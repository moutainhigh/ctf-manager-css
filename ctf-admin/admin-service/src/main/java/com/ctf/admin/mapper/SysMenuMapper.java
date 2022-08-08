package com.ctf.admin.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ctf.admin.pojo.entity.SysMenu;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;


@Mapper
public interface SysMenuMapper extends BaseMapper<SysMenu> {
    /**
     * 获取菜单路由
     * @return
     */
    List<SysMenu> listRoutes();

}
