package com.ctf.admin.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ctf.admin.mapper.SysRolePermissionMapper;
import com.ctf.admin.pojo.entity.SysRolePermission;
import com.ctf.admin.service.SysRolePermissionService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 角色权限实现类
 */
@Service
public class SysRolePermissionServiceImpl extends ServiceImpl<SysRolePermissionMapper, SysRolePermission> implements SysRolePermissionService {

    /**
     * 获取角色拥有的权限ID集合
     *
     * @param roleId
     * @return
     */
    @Override
    public List<Long> listPermIdsByRoleId(Long roleId) {
        List<Long> permIds = this.baseMapper.listPermIdsByRoleId(roleId);
        return permIds;
    }

}
