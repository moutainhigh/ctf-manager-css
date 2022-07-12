package com.zdf.auth.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zdf.auth.model.bo.QuerySysDeptForm;
import com.zdf.auth.model.po.SysMenu;
import com.zdf.auth.model.po.SysUser;
import com.zdf.auth.service.ISysUserService;
import com.zdf.common.dto.AuthUserDetail;
import com.zdf.common.enums.DepartTypeEnum;
import com.zdf.common.exception.JayudBizException;
import com.zdf.common.utils.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import com.zdf.common.utils.CurrentUserUtil;
import com.zdf.auth.model.po.SysDepart;
import com.zdf.auth.mapper.SysDepartMapper;
import com.zdf.auth.service.ISysDepartService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * 组织机构表 服务实现类
 *
 * @author jayud
 * @since 2022-02-22
 */
@Slf4j
@Service
public class SysDepartServiceImpl extends ServiceImpl<SysDepartMapper, SysDepart> implements ISysDepartService {


    @Autowired
    private SysDepartMapper sysDepartMapper;
    @Autowired
    private ISysUserService sysUserService;


    @Override
    public IPage<SysDepart> selectPage(SysDepart sysDepart,
                                       Integer currentPage,
                                       Integer pageSize,
                                       HttpServletRequest req) {

        Page<SysDepart> page = new Page<SysDepart>(currentPage, pageSize);
        IPage<SysDepart> pageList = sysDepartMapper.pageList(page, sysDepart);
        return pageList;
    }

    @Override
    public List<SysDepart> selectList(SysDepart sysDepart) {
        return sysDepartMapper.list(sysDepart);
    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public void phyDelById(Long id) {
        sysDepartMapper.phyDelById(id);
    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public void logicDel(Long id) {
        //存在员工，不能删除
        QueryWrapper<SysUser> sysUserQueryWrapper = new QueryWrapper<>();
        sysUserQueryWrapper.lambda().eq(SysUser::getDepartId, id);
        sysUserQueryWrapper.lambda().eq(SysUser::getIsDeleted, 0);
        List<SysUser> list = sysUserService.list(sysUserQueryWrapper);
        if (CollUtil.isNotEmpty(list)) {
            throw new IllegalArgumentException("组织存在员工，不能删除");
        }
        //存在子级，不能删除
        QueryWrapper<SysDepart> sysDepartQueryWrapper = new QueryWrapper<>();
        sysDepartQueryWrapper.lambda().eq(SysDepart::getParentId, id);
        sysDepartQueryWrapper.lambda().eq(SysDepart::getIsDeleted, 0);
        List<SysDepart> list1 = sysDepartMapper.selectList(sysDepartQueryWrapper);
        if (CollUtil.isNotEmpty(list1)) {
            throw new IllegalArgumentException("组织存在子级，不能删除");
        }
        sysDepartMapper.logicDel(id, CurrentUserUtil.getUsername());
    }

    @Override
    public List<SysDepart> selectDeptTree(QuerySysDeptForm form) {
        //组织区分租户
        AuthUserDetail userDetail = CurrentUserUtil.getUserDetail();
        SysUser sysUser = sysUserService.getById(userDetail.getId());
        String tenantCode = sysUser.getCode();
        form.setTenantCode(tenantCode);
        List<SysDepart> departs = sysDepartMapper.selectDeptTree(form);
        List<SysDepart> tree = buildTree(departs, "0");
        return tree;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveSysDepart(SysDepart depart) {
        Long id = depart.getId();
        SysDepart sysDepart = this.getById(id);
        if (ObjectUtil.isEmpty(sysDepart)) {
            //新增
//            QueryWrapper<SysDepart> queryWrapper = new QueryWrapper<>();
//            queryWrapper.lambda().eq(SysDepart::getIsDeleted, 0);
//            queryWrapper.lambda().eq(SysDepart::getOrgCode, depart.getOrgCode());
//            queryWrapper.lambda().groupBy(SysDepart::getOrgCode);
//            SysDepart one = this.getOne(queryWrapper);
//            if (ObjectUtil.isNotEmpty(one)) {
//                throw new IllegalArgumentException("机构编码已存在");
//            }
            depart.setCreateBy(CurrentUserUtil.getUsername());
            depart.setCreateTime(new Date());
        } else {
            //修改
//            QueryWrapper<SysDepart> queryWrapper = new QueryWrapper<>();
//            queryWrapper.lambda().ne(SysDepart::getId, id);
//            queryWrapper.lambda().eq(SysDepart::getIsDeleted, 0);
//            queryWrapper.lambda().eq(SysDepart::getOrgCode, depart.getOrgCode());
//            queryWrapper.lambda().groupBy(SysDepart::getOrgCode);
//            SysDepart one = this.getOne(queryWrapper);
//            if (ObjectUtil.isNotEmpty(one)) {
//                throw new IllegalArgumentException("机构编码已存在");
//            }
            depart.setUpdateBy(CurrentUserUtil.getUsername());
            depart.setUpdateTime(new Date());
        }
        //获取当前用户租户编码
        String orgCategory = depart.getOrgCategory();
        if (orgCategory.equals("1")) {
            String userTenantCode = CurrentUserUtil.getUserTenantCode();
            QueryWrapper<SysDepart> sysDepartQueryWrapper = new QueryWrapper<>();
            sysDepartQueryWrapper.lambda().eq(SysDepart::getIsDeleted, 0);
            sysDepartQueryWrapper.lambda().eq(SysDepart::getTenantCode, userTenantCode);
            sysDepartQueryWrapper.lambda().eq(SysDepart::getOrgCategory, 1);
            sysDepartQueryWrapper.lambda().groupBy(SysDepart::getOrgCode);
            SysDepart one = this.getOne(sysDepartQueryWrapper);
            if (ObjectUtil.isNotEmpty(one)) {
                throw new IllegalArgumentException("一个租户仅能存在一个集团");
            }
        }
        //Long parentId = ObjectUtil.isEmpty(depart.getParentId()) ? 0L : depart.getParentId();
        Long parentId = 0L;
        List<Long> parentIdList = depart.getParentIdList();
        if (CollUtil.isNotEmpty(parentIdList)) {
            parentId = parentIdList.get(parentIdList.size() - 1);
        }
        depart.setParentId(parentId);

        this.saveOrUpdate(depart);
    }

    @Override
    public List<SysDepart> selectSuperiorOrganization(QuerySysDeptForm form) {
        //组织区分租户
        AuthUserDetail userDetail = CurrentUserUtil.getUserDetail();
        SysUser sysUser = sysUserService.getById(userDetail.getId());
        String tenantCode = sysUser.getCode();
        form.setTenantCode(tenantCode);

        //机构类别 1集团，2公司，3部门
        String orgCategory = form.getOrgCategory();
        List<String> stringList = new ArrayList<>();
        if (DepartTypeEnum.GROUP.getDepartType().equals(orgCategory)) {
            stringList = Arrays.asList("1", "2", "3");
        } else if (DepartTypeEnum.COMPANY.getDepartType().equals(orgCategory)) {
            stringList = Arrays.asList("2", "3");
        } else if (DepartTypeEnum.DEPART.getDepartType().equals(orgCategory)) {
            stringList = Arrays.asList("3");
        }
        //过滤掉的机构类别
        form.setNotInOrgCategory(stringList);
        List<SysDepart> departs = sysDepartMapper.selectDeptTree(form);
        List<SysDepart> tree = buildTree(departs, "0");
        return tree;
    }

    @Override
    public SysDepart queryById(int id) {
        SysDepart depart = this.getById(id);
        if (ObjectUtil.isEmpty(depart)) {
            throw new IllegalArgumentException("组织不存在");
        }
        String parentIds = sysDepartMapper.selectParentIds(depart.getId());
        String[] parentIdArrays = parentIds.split(",");
        List<Long> parentIdList = new ArrayList<>();
        for (int i = parentIdArrays.length; i > 0; i--) {
            parentIdList.add(Long.valueOf(parentIdArrays[i - 1]));
        }
        //parentIdList.add(depart.getId());
        depart.setParentIdList(parentIdList);
        return depart;
    }

    /**
     * 构建树
     *
     * @param list
     * @param pid
     * @return
     */
    private List<SysDepart> buildTree(List<SysDepart> list, String pid) {
        List<SysDepart> treeList = new ArrayList<>();
        list.forEach(l -> {
            if (StrUtil.equals(pid, l.getParentId().toString())) {
                l.setChildren(buildTree(list, l.getId().toString()));
                treeList.add(l);
            }
        });
        return treeList;
    }


    //构建树并且查询员工信息
    @Override
    public List<SysDepart> selectDeptTreeStaff(QuerySysDeptForm form) {
        //组织区分租户
        AuthUserDetail userDetail = CurrentUserUtil.getUserDetail();
        SysUser sysUser = sysUserService.getById(userDetail.getId());
        String tenantCode = sysUser.getCode();
        form.setTenantCode(tenantCode);
        List<SysDepart> departs = sysDepartMapper.selectDeptTree(form);
        List<SysDepart> tree = buildTreeStaff(departs, "0");
        return tree;
    }

    @Override
    public List<SysDepart> getByOrgCategory(Integer orgCategory) {
        return this.list(new QueryWrapper<>(new SysDepart().setIsDeleted(false).setOrgCategory(orgCategory.toString())));
    }

    @Override
    public List<SysDepart> slectChildrenById(Long id) {
        return sysDepartMapper.slectChildrenById(id);
    }

    @Override
    public SysDepart getByOrgCode(String depart) {
        QueryWrapper<SysDepart> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(SysDepart::getIsDeleted, 0);
        queryWrapper.lambda().eq(SysDepart::getOrgCode, depart);
        return this.getOne(queryWrapper);
    }

    @Override
    public List<SysDepart> selectOperationSubject(QuerySysDeptForm form) {
        AuthUserDetail userDetail = CurrentUserUtil.getUserDetail();
        SysUser sysUser = sysUserService.getById(userDetail.getId());
        String tenantCode = sysUser.getCode();
        form.setTenantCode(tenantCode);
        return this.baseMapper.selectOperationSubject(form);
    }

    @Override
    public List<SysDepart> getSysDepart() {
        QueryWrapper<SysDepart> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(SysDepart::getIsDeleted, 0);
        queryWrapper.lambda().eq(SysDepart::getOrgCategory, 3);
        queryWrapper.lambda().eq(SysDepart::getTenantCode,CurrentUserUtil.getUserTenantCode());
        return this.list(queryWrapper);
    }


    //构建树并且查询员工信息
    private List<SysDepart> buildTreeStaff(List<SysDepart> list, String pid) {
        List<SysDepart> treeList = new ArrayList<>();
        list.forEach(l -> {
            if (StrUtil.equals(pid, l.getParentId().toString())) {
                l.setChildren(buildTreeStaff(list, l.getId().toString()));
                if (StrUtil.equals(DepartTypeEnum.DEPART.getDepartType(), l.getOrgCategory())) {
                    QueryWrapper<SysUser> sysUserQueryWrapper = new QueryWrapper<>();
                    sysUserQueryWrapper.lambda().eq(SysUser::getDepartId, l.getId());
                    sysUserQueryWrapper.lambda().eq(SysUser::getIsDeleted, 0);
                    List<SysUser> lists = sysUserService.list(sysUserQueryWrapper);
                    List<SysDepart> treeListUser = new ArrayList<>();
                    SysDepart sysDeparts = null;
                    for (int i = 0; i < lists.size(); i++) {
                        long iil = 111111;
                        sysDeparts = new SysDepart();
                        Long aLong = StringUtils.longTransitionLong(iil, lists.get(i).getId());
                        sysDeparts.setId(aLong);
                        sysDeparts.setUserId(lists.get(i).getId());
                        sysDeparts.setLabel(lists.get(i).getName());
                        treeListUser.add(sysDeparts);
                    }
                    l.setChildren(treeListUser);
                }
                treeList.add(l);
            }
        });
        return treeList;
    }

}
