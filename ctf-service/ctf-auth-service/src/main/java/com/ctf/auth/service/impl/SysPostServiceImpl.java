package com.ctf.auth.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.google.common.base.Strings;
import com.ctf.auth.mapper.SysUserMapper;
import com.ctf.auth.model.bo.SysPostForm;
import com.ctf.auth.model.po.SysDepart;
import com.ctf.auth.model.po.SysUser;
import com.ctf.auth.model.vo.SysPostVO;
import com.ctf.auth.model.vo.SysUserVO;
import com.ctf.auth.service.ISysUserService;
import com.ctf.common.BaseResult;
import com.ctf.common.constant.SysTips;
import com.ctf.common.exception.JayudBizException;
import com.ctf.common.utils.ConvertUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import com.ctf.common.utils.CurrentUserUtil;
import com.ctf.auth.model.po.SysPost;
import com.ctf.auth.mapper.SysPostMapper;
import com.ctf.auth.service.ISysPostService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * 岗位表 服务实现类
 *
 * @author jayud
 * @since 2022-02-22
 */
@Slf4j
@Service
public class SysPostServiceImpl extends ServiceImpl<SysPostMapper, SysPost> implements ISysPostService {


    @Autowired
    private SysPostMapper sysPostMapper;

    @Autowired
    public ISysUserService sysUserService;

    @Override
    public IPage<SysPost> selectPage(SysPost sysPost,
                                     Integer currentPage,
                                     Integer pageSize,
                                     HttpServletRequest req) {

        Page<SysPost> page = new Page<SysPost>(currentPage, pageSize);
        IPage<SysPost> pageList = sysPostMapper.pageList(page, sysPost);


        return pageList;
    }

    @Override
    public List<SysPost> selectList(SysPost sysPost) {
        return sysPostMapper.list(sysPost);
    }

    @Override
    public List<SysPostVO> selectSysPostLists(SysPostForm sysPost) {


        List<SysPostVO> sysPostVOS = sysPostMapper.selectSysPostLists(sysPost);

        List<SysPostVO> sysPostVOS1 = buildTree(sysPostVOS, "0");
        return sysPostVOS1;
    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public void phyDelById(Long id) {
        sysPostMapper.phyDelById(id);
    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public void logicDel(Long id) {
        sysPostMapper.logicDel(id, CurrentUserUtil.getUsername());
    }

    @Override
    public boolean saveOrUpdateSysPost(SysPostForm sysPostForm) {


        Boolean result = null;
        SysPost sysPost = ConvertUtil.convert(sysPostForm, SysPost.class);
        if (sysPost.getId() != null) {


            SysPost sysUserOne = findSysUserOne(sysPostForm.getPostCode(), null, sysPost.getId());
            if (sysUserOne != null) {
                throw new JayudBizException("岗位编码已存在");
            }
            SysPost two = findSysUserOne(null, sysPostForm.getPostName(), sysPost.getId());
            if (two != null) {
                throw new JayudBizException("岗位名称已存在！");
            }
            if(sysPost.getPostSequence()==null){
                sysPost.setPostSequence(0L);
            }
            //修改
            sysPost.setUpdateBy(CurrentUserUtil.getUsername());
            sysPost.setUpdateTime(new Date());
            result = this.updateById(sysPost);
        } else {

            SysPost sysUserOne = findSysUserOne(sysPostForm.getPostCode(), null, null);
            if (sysUserOne != null) {
                throw new JayudBizException("岗位编码已存在");
            }
            SysPost two = findSysUserOne(null, sysPostForm.getPostName(), null);
            if (two != null) {
                throw new JayudBizException("岗位名称已存在！");
            }

            sysPost.setCreateBy(CurrentUserUtil.getUsername());
            sysPost.setCreateTime(new Date());
            result = this.save(sysPost);
        }
        if (result) {
            log.warn("新增或修改库区成功");
            return true;
        }

        return false;
    }

    @Override
    public BaseResult deleteSysPost(List<Long> ids) {

        List<SysPost> sysPosts = new ArrayList<>();
        for (int i = 0; i < ids.size(); i++) {
            //判断 判断有关联的岗位不可删除
            SysUser sysUser = new SysUser();
            sysUser.setPostId(ids.get(i));
            List<SysUserVO> sysUserVOS = sysUserService.selectList(sysUser);
            if (sysUserVOS.size() != 0) {
//                throw new JayudBizException("有存在岗位绑定关系,无法删除！");
                return BaseResult.error(SysTips.POST_INCIDENCE_RELATION_ONE);
            }
            SysPost sysPost = new SysPost();
            sysPost.setId(ids.get(i));
            sysPost.setIsDeleted(true);
            sysPosts.add(sysPost);
        }
        this.updateBatchById(sysPosts);
        return BaseResult.ok(SysTips.DEL_SUCCESS);
    }


    /**
     * 构建树
     *
     * @param list
     * @param pid
     * @return
     */
    private List<SysPostVO> buildTree(List<SysPostVO> list, String pid) {
        List<SysPostVO> treeList = new ArrayList<>();
        list.forEach(l -> {
            if (StrUtil.equals(pid, l.getParentId().toString())) {
                l.setChildren(buildTree(list, l.getId().toString()));
                treeList.add(l);
            }
        });
        return treeList;
    }


    //判断岗位和编码
    public SysPost findSysUserOne(String postCode, String postName, Long id) {
        LambdaQueryWrapper<SysPost> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        //编码
        if (StringUtils.isNotBlank(postCode)) {
            lambdaQueryWrapper.eq(SysPost::getIsDeleted, false);
            lambdaQueryWrapper.eq(SysPost::getPostCode, postCode);
        }
        //名称
        if (StringUtils.isNotBlank(postName)) {
            lambdaQueryWrapper.eq(SysPost::getIsDeleted, false);
            lambdaQueryWrapper.eq(SysPost::getPostName, postName);
        }
        if (id != null) {
            //不等于
            lambdaQueryWrapper.ne(SysPost::getId, id);
        }

        return this.getOne(lambdaQueryWrapper);
    }
}
