package com.ctf.admin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ctf.admin.dto.UserAuthDTO;
import com.ctf.admin.pojo.entity.SysUser;
import com.ctf.admin.pojo.po.UserFormPO;
import com.ctf.admin.pojo.po.UserPO;
import com.ctf.admin.pojo.query.UserPageQuery;
import com.ctf.admin.pojo.vo.user.UserExportVO;
import com.ctf.common.mybatis.annotation.DataPermission;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;


@Mapper
public interface SysUserMapper extends BaseMapper<SysUser> {

    /**
     * 获取用户分页列表
     *
     * @param page
     * @param queryParams 查询参数
     * @return
     */
    @DataPermission(deptAlias = "d")
    Page<UserPO> listUserPages(Page<UserPO> page, UserPageQuery queryParams);

    /**
     * 获取用户表单详情
     *
     * @param userId 用户ID
     * @return
     */
    UserFormPO getUserFormData(Long userId);

    /**
     * 根据用户名获取认证信息
     *
     * @param username
     * @return
     */
    UserAuthDTO getAuthInfoByUsername(String username);

    /**
     * 获取导出用户列表
     *
     * @param queryParams
     * @return
     */
    @DataPermission(deptAlias = "d")
    List<UserExportVO> listExportUsers(UserPageQuery queryParams);
}
