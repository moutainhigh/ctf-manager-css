package com.ctf.auth.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ctf.auth.model.bo.SysTenantForm;
import com.ctf.auth.model.po.SysRole;
import com.ctf.auth.service.ISysMenuService;
import com.ctf.auth.service.ISysRoleMenuService;
import com.ctf.auth.service.ISysRoleService;
import com.ctf.common.utils.ListUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import com.ctf.common.utils.CurrentUserUtil;
import com.ctf.auth.model.po.SysTenantToSystem;
import com.ctf.auth.mapper.SysTenantToSystemMapper;
import com.ctf.auth.service.ISysTenantToSystemService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 租户-系统关联表 服务实现类
 *
 * @author jayud
 * @since 2022-02-21
 */
@Slf4j
@Service
public class SysTenantToSystemServiceImpl extends ServiceImpl<SysTenantToSystemMapper, SysTenantToSystem> implements ISysTenantToSystemService {


    @Autowired
    private SysTenantToSystemMapper sysTenantToSystemMapper;

    @Autowired
    private ISysRoleService sysRoleService;

    @Autowired
    private ISysMenuService sysMenuService;

    @Autowired
    private ISysRoleMenuService sysRoleMenuService;

    @Override
    public IPage<SysTenantToSystem> selectPage(SysTenantToSystem sysTenantToSystem,
                                        Integer currentPage,
                                        Integer pageSize,
                                        HttpServletRequest req){

        Page<SysTenantToSystem> page=new Page<SysTenantToSystem>(currentPage,pageSize);
        IPage<SysTenantToSystem> pageList= sysTenantToSystemMapper.pageList(page, sysTenantToSystem);
        return pageList;
    }

    @Override
    public List<SysTenantToSystem> selectList(SysTenantToSystem sysTenantToSystem){
        return sysTenantToSystemMapper.list(sysTenantToSystem);
    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public void phyDelById(Long id){
        sysTenantToSystemMapper.phyDelById(id);
    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public void logicDel(Long id){
        sysTenantToSystemMapper.logicDel(id,CurrentUserUtil.getUsername());
    }

    @Override
    public void saveTenantSystemRelation(SysTenantForm sysTenantForm) {
        SysTenantToSystem sysTenantToSystem = new SysTenantToSystem();
        sysTenantToSystem.setTenantId(sysTenantForm.getId());
        List<SysTenantToSystem> tenantToSystemList = selectList(sysTenantToSystem);
        List<String> lastIdList = tenantToSystemList.stream().map(x->String.valueOf(x.getSystemId())).distinct().collect(Collectors.toList());
        List<String> thisIdList = sysTenantForm.getSysUrlList().stream().map(x->String.valueOf(x.getId())).distinct().collect(Collectors.toList());
        List<String> addIdList = ListUtil.getDiff(lastIdList,thisIdList);
        List<String> delIdList = ListUtil.getDiff(thisIdList,lastIdList);
        if (CollUtil.isNotEmpty(addIdList)){
            List<SysTenantToSystem> addList = new ArrayList<>();
            addIdList.forEach(id->{
                SysTenantToSystem system = new SysTenantToSystem();
                system.setSystemId(Long.valueOf(id));
                system.setTenantId(sysTenantForm.getId());
                addList.add(system);
            });
            this.saveBatch(addList);
        }
        if (CollUtil.isNotEmpty(delIdList)){
            sysTenantToSystemMapper.deletedRelation(sysTenantForm.getId(),delIdList,CurrentUserUtil.getUsername());
            //删除该租户下所有角色对该系统菜单的关联关系
            //获取租户下所有角色
            List<SysRole> roleByTenantCode = sysRoleService.getRoleByTenantCode(sysTenantForm.getCode());
            List<Long> roleIds = new ArrayList<>();
            for (SysRole sysRole : roleByTenantCode) {
                roleIds.add(sysRole.getId());
            }
            //获取系统下所有菜单
            List<Long> menuIds = sysMenuService.getAllMenuBySysType(delIdList);
            if(CollectionUtil.isNotEmpty(menuIds)){
                //删除角色与菜单的关联关系
                boolean result = sysRoleMenuService.deleteByRoleIdsAndMenuIds(roleIds,menuIds);
                if(result){
                    log.warn("删除该租户下所有角色对该系统菜单的关联关系数据成功");
                }
            }

        }
    }

}
