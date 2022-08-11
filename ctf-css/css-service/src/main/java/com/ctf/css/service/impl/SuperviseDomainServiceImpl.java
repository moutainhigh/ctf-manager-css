package com.ctf.css.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ctf.css.converter.SuperviseDomainConverter;
import com.ctf.css.mapper.SuperviseDomainMapper;
import com.ctf.css.pojo.entity.SuperviseDomain;
import com.ctf.css.pojo.query.SupervisorDomainPageQuery;
import com.ctf.css.pojo.vo.ex.SuperviseDomainVo;
import com.ctf.css.service.SuperviseDomainService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
* @author zhangyizheng
* @description 针对表【store_supervise_domain(督导领域表)】的数据库操作Service实现
* @createDate 2022-08-09 20:47:20
*/
@Service
@RequiredArgsConstructor
public class SuperviseDomainServiceImpl extends ServiceImpl<SuperviseDomainMapper, SuperviseDomain>
    implements SuperviseDomainService{

    private final SuperviseDomainMapper superviseDomainMapper;
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
}




