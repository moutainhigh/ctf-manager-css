package com.ctf.css.service.impl;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ctf.common.web.domain.Option;
import com.ctf.css.converter.SuperviseDomainConverter;
import com.ctf.css.mapper.SuperviseDomainMapper;
import com.ctf.css.pojo.entity.SuperviseDomain;
import com.ctf.css.pojo.form.SuperviseDomainForm;
import com.ctf.css.pojo.query.SupervisorDomainPageQuery;
import com.ctf.css.pojo.vo.ex.SuperviseDomainVo;
import com.ctf.css.service.SuperviseDomainService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
* @author zhangyizheng
* @description 针对表【store_supervise_domain(督导领域表)】的数据库操作Service实现
* @createDate 2022-08-09 20:47:20
*/
@Service
@RequiredArgsConstructor
public class SuperviseDomainServiceImpl extends ServiceImpl<SuperviseDomainMapper, SuperviseDomain>
    implements SuperviseDomainService{

    private final SuperviseDomainConverter superviseDomainConverter;

    @Override
    public Page<SuperviseDomainVo> pageSupervise(SupervisorDomainPageQuery queryParams) {
        LambdaQueryWrapper<SuperviseDomain> wrapper = new LambdaQueryWrapper<>();
        //构造条件构造器
        if (StrUtil.isNotEmpty(queryParams.getSuperviseDomainName())) {
            wrapper.like(SuperviseDomain::getSuperviseDomainName,queryParams.getSuperviseDomainName());
        }
        if (StrUtil.isNotEmpty(queryParams.getStatus())) {
            wrapper.eq(SuperviseDomain::getStatus,queryParams.getStatus());
        }
        //进行分页查询
        Page<SuperviseDomain> page = new Page<>();
        page.setCurrent(queryParams.getPageNum());
        page.setSize(queryParams.getPageSize());
        page = this.baseMapper.selectPage(page, wrapper);
        //利用转换器将Po对象转为Vo对象
        return superviseDomainConverter.pageEntityToVo(page);
    }

    @Transactional(rollbackFor = RuntimeException.class)
    @Override
    public boolean saveSuperviseDomain(SuperviseDomainForm superviseDomainForm) {
        SuperviseDomain superviseDomain = new SuperviseDomain();
        QueryWrapper<SuperviseDomain> objectQueryWrapper = new QueryWrapper<>();
        //判断督导领域名称是否存在
        if (!ObjectUtil.isNotEmpty(this.baseMapper.selectOne(objectQueryWrapper
                .eq("supervise_domain_name", superviseDomainForm.getSuperviseDomainName())))) {
            superviseDomain.setSuperviseDomainName(superviseDomainForm.getSuperviseDomainName());
            superviseDomain.setStatus(superviseDomainForm.getStatus());
            int i = this.baseMapper.insert(superviseDomain);
            if (i==1){
                return true;
            }else {
                return false;
            }
        }else {
            throw new RuntimeException("该督导领域名称已存在");
        }
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean updateSuperviseDomain(Long superviseDomainId, SuperviseDomainForm superviseDomainForm) {
        QueryWrapper<SuperviseDomain> objectQueryWrapper = new QueryWrapper<>();
        System.out.println("这里"+this.baseMapper.selectOne(objectQueryWrapper
                        .eq("supervise_domain_name", superviseDomainForm.getSuperviseDomainName()))
                .getId());
        //判断督导领域名称是否存在
        if (!ObjectUtil.isNotEmpty(this.baseMapper.selectOne(objectQueryWrapper
                .eq("supervise_domain_name", superviseDomainForm.getSuperviseDomainName())))) {
            SuperviseDomain superviseDomain = this.baseMapper.selectById(superviseDomainId);
            superviseDomain.setStatus(superviseDomainForm.getStatus());
            superviseDomain.setSuperviseDomainName(superviseDomainForm.getSuperviseDomainName());
            //根据id修改督导领域名称
            return this.baseMapper.updateById(superviseDomain) >0;
        }else {
            //判断是否不修改名称
            if (Objects.equals(this.baseMapper.selectOne(objectQueryWrapper
                            .eq("supervise_domain_name", superviseDomainForm.getSuperviseDomainName()))
                    .getId(), superviseDomainId)){
                SuperviseDomain superviseDomain = this.baseMapper.selectById(superviseDomainId);
                superviseDomain.setStatus(superviseDomainForm.getStatus());
                superviseDomain.setSuperviseDomainName(superviseDomainForm.getSuperviseDomainName());
                //根据id修改督导领域名称
                return this.baseMapper.updateById(superviseDomain) >0;
            }else {
                throw new RuntimeException("该督导领域名称已存在");
            }
        }
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean deleteSuperviseDomain(String superviseDomainIds) {
        String[] split = superviseDomainIds.split(",|，");
        List<Long> collect = Arrays.stream(split).map(Long::parseLong).collect(Collectors.toList());
        return this.baseMapper.deleteBatchIds(collect)>0;
    }

    @Override
    public List<Option> listDomainOptions() {
        List<SuperviseDomain> domainList = this.list(new LambdaQueryWrapper<SuperviseDomain>()
                .select(SuperviseDomain::getId,SuperviseDomain::getSuperviseDomainName)
        );

        //实体转换
        List<Option> list = superviseDomainConverter.domain2Options(domainList);
        return list;
    }
}




