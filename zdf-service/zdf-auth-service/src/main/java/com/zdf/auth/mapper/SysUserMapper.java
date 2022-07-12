package com.zdf.auth.mapper;

import com.zdf.auth.model.bo.SysUserForm;
import com.zdf.auth.model.po.SysUser;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import com.zdf.auth.model.vo.SysUserVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.poi.openxml4j.util.ZipFileZipEntrySource;

import java.util.List;

/**
 * 后台用户表 Mapper 接口
 *
 * @author jayud
 * @since 2022-02-21
 */
@Mapper
public interface SysUserMapper extends BaseMapper<SysUser> {

    /**
     * @description 分页查询
     * @author jayud
     * @date 2022-02-21
     * @param: page
     * @param: sysUser
     * @return: com.baomidou.mybatisplus.core.metadata.IPage<com.zdf.auth.model.po.SysUser>
     **/
    IPage<SysUserVO> pageList(@Param("page") Page<SysUserForm> page, @Param("sysUser") SysUserForm sysUser);

    /**
     * @description 列表查询数据
     * @author jayud
     * @date 2022-02-21
     * @param: sysUser
     * @return: java.util.List<com.zdf.auth.model.po.SysUser>
     **/
    List<SysUserVO> list(@Param("sysUser") SysUser sysUser);

    //根据id集合查询用户信息   没用到
    List<SysUserVO> findSelectIdsList(@Param("sysUser") SysUserForm sysUser);

    /**
     * @description 根据id物理删除
     * @author jayud
     * @date 2022-02-21
     * @param: id
     * @return: int
     **/
    int phyDelById(@Param("id") Long id);

    /**
     * @description 根据id逻辑删除
     * @author jayud
     * @date 2022-02-21
     * @param: id
     * @param: username
     * @return: int
     **/
    int logicDel(@Param("id") Long id, @Param("username") String username);


    //根据用户名称查询信息 校验

    SysUserVO findSysUserNameOne(@Param("sysUser") SysUserForm sysUser);

    //编辑 根据id查询信息
    SysUserVO findSysUserIdOne(@Param("sysUser") SysUserForm sysUser);

    /**
     * 根据名称获取用户信息
     * @param token
     * @return
     */
    SysUserVO getSystemUserByName(@Param("token") String token);

    /**
     * @description 根据角色编码查询用户
     * @author  ciro
     * @date   2022/3/3 9:45
     * @param: roleCode
     * @param: tenantCode
     * @return: java.util.List<com.zdf.auth.model.po.SysUser>
     **/
    List<SysUser> selectUserByRoleCode(@Param("roleCode") String roleCode,@Param("tenantCode") String tenantCode);
}
