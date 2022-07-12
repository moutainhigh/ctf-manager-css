package com.zdf.auth.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zdf.auth.mapper.SysRoleActionDataMapper;
import com.zdf.auth.model.bo.AddSysRoleActionDataForm;
import com.zdf.auth.model.bo.QueryForm;
import com.zdf.auth.model.enums.ActionEnum;
import com.zdf.auth.model.po.SysRole;
import com.zdf.auth.model.po.SysRoleActionData;
import com.zdf.auth.model.po.SysUser;
import com.zdf.auth.model.vo.SysRoleActionDataVO;
import com.zdf.auth.model.vo.SysUserVO;
import com.zdf.auth.service.ISysMenuService;
import com.zdf.auth.service.ISysRoleActionDataService;
import com.zdf.auth.service.ISysUserRoleService;
import com.zdf.auth.service.ISysUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zdf.common.BaseResult;
import com.zdf.common.utils.CurrentUserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 角色数据权限 服务实现类
 * </p>
 *
 * @author LLJ
 * @since 2021-08-02
 */
@Service
public class SysRoleActionDataServiceImpl extends ServiceImpl<SysRoleActionDataMapper, SysRoleActionData> implements ISysRoleActionDataService {

    @Autowired
    private ISysUserService systemUserService;

    @Autowired
    private ISysMenuService systemMenuService;

    @Autowired
    private ISysUserRoleService systemUserRoleRelationService;

    @Override
    public IPage<SysRoleActionDataVO> findByPage(QueryForm form) {
        Page<SysRoleActionDataVO> page = new Page<>(form.getPageNum(), form.getPageSize());
        return this.baseMapper.findByPage(page, form);
    }

    @Override
    public boolean addSystemRoleActionData(AddSysRoleActionDataForm form) {
        SysUserVO systemUser = systemUserService.getSystemUserByName(CurrentUserUtil.getUsername());

        for (SysRoleActionData sysRoleActionData : form.getSysRoleActionDatas()) {

            if(null == sysRoleActionData.getId()){
                sysRoleActionData.setCrtBy(systemUser.getId().intValue());
                sysRoleActionData.setCrtByDtm(LocalDateTime.now());
                sysRoleActionData.setCrtByName(systemUser.getUserName());
            }else {
                sysRoleActionData.setMdyBy(systemUser.getId().intValue());
                sysRoleActionData.setMdyByDtm(LocalDateTime.now());
                sysRoleActionData.setMdyByName(systemUser.getUserName());
            }
        }

        boolean save = this.saveOrUpdateBatch(form.getSysRoleActionDatas());
        if(!save){
            log.warn("角色数据权限增加失败");
        }
        return save;
    }

    /**
     * 获取数据权限
     */
    @Override
    public List<Integer> getRoleData(String actionCode){
        SysUserVO systemUser = systemUserService.getSystemUserByName(CurrentUserUtil.getUsername());
        List<SysRole> enabledRolesByUserId = systemUserRoleRelationService.getEnabledRolesByUserId(systemUser.getId());
        List<Long> longs = new ArrayList<>();
        for (SysRole systemRole : enabledRolesByUserId) {
            longs.add(systemRole.getId());
        }
        List<Integer> list = new ArrayList<>();
        Integer result = this.baseMapper.getRoleData(actionCode,longs);
        if(result != null){
            if(result.equals(ActionEnum.ZERO.getCode())){
                list.add(0);

            }
            if(result.equals(ActionEnum.ONE.getCode())){
                list.add(systemUser.getId().intValue());
                return list;
            }
            if(result.equals(ActionEnum.TWO.getCode())){
                List<SysUser> systemUsers = systemUserService.getSysUserByDepartmentId(systemUser.getDepartId());
                for (SysUser user : systemUsers) {
                    list.add(user.getId().intValue());
                }
                return list;
            }
            if(result.equals(ActionEnum.THREE.getCode())){
                return null;
            }
        }
        return list;
    }

    @Override
    public BaseResult<List<SysRoleActionData>> selectList(SysRoleActionData sysRoleActionData) {
        QueryWrapper<SysRoleActionData> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(SysRoleActionData::getVoided,0);
        queryWrapper.lambda().eq(SysRoleActionData::getRoleId,sysRoleActionData.getRoleId());
        return BaseResult.ok(this.list(queryWrapper));
    }
}
