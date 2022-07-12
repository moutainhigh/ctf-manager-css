package com.zdf.auth.mapper;

import com.zdf.auth.model.po.SysRole;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import com.zdf.auth.model.vo.SysRoleVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * 角色表 Mapper 接口
 *
 * @author jayud
 * @since 2022-02-21
 */
@Mapper
public interface SysRoleMapper extends BaseMapper<SysRole> {

    /**
     * @description 分页查询
     * @author  jayud
     * @date   2022-02-21
     * @param: page
     * @param: sysRole
     * @return: com.baomidou.mybatisplus.core.metadata.IPage<com.zdf.auth.model.po.SysRole>
     **/
    IPage<SysRoleVO> pageList(@Param("page") Page<SysRole> page, @Param("sysRole") SysRole sysRole);

    /**
     * @description 列表查询数据
     * @author  jayud
     * @date   2022-02-21
     * @param: sysRole
     * @return: java.util.List<com.zdf.auth.model.po.SysRole>
     **/
    List<SysRole> list(@Param("sysRole") SysRole sysRole);


    /**
     * @description 根据id物理删除
     * @author  jayud
     * @date   2022-02-21
     * @param: id
     * @return: int
     **/
    int phyDelById(@Param("id") Long id);

    /**
     * @description 根据id逻辑删除
     * @author  jayud
     * @date   2022-02-21
     * @param: id
     * @param: username
     * @return: int
     **/
    int logicDel(@Param("id") Long id,@Param("username") String username);

    /**
     * 根据用户id，查询关联的角色
     * @param userId
     * @return
     */
    List<SysRole> selectSysRoleByUserId(@Param("userId") Long userId);

    /**
     * @description 根据用户名称查询角色
     * @author  ciro
     * @date   2022/2/24 10:54
     * @param: username
     * @return: java.util.List<com.zdf.auth.model.po.SysRole>
     **/
    List<SysRole> selectRoleByUsername(@Param("username") String username);
}
