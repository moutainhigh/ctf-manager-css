package com.ctf.css.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ctf.css.converter.TourSchemeConverter;
import com.ctf.css.pojo.entity.SuperviseDomain;
import com.ctf.css.pojo.entity.TourScheme;
import com.ctf.css.pojo.query.TourSchemePageQuery;
import com.ctf.css.pojo.vo.ex.TourSchemeVO;
import com.ctf.css.service.SuperviseDomainService;
import com.ctf.css.service.TourSchemeService;
import com.ctf.css.mapper.TourSchemeMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
* @author zhangyizheng
* @description 针对表【store_tour_scheme(巡检方案表)】的数据库操作Service实现
* @createDate 2022-08-09 20:47:20
*/
@Service
@RequiredArgsConstructor
public class TourSchemeServiceImpl extends ServiceImpl<TourSchemeMapper, TourScheme>
    implements TourSchemeService{

    private final TourSchemeConverter tourSchemeConverter;
    private final SuperviseDomainService superviseDomainService;

    @Override
    public Page<TourSchemeVO> pageTourScheme(TourSchemePageQuery queryParams) {
        Page<TourSchemeVO> page = new Page<>();
        page.setCurrent(queryParams.getPageNum());
        page.setSize(queryParams.getPageSize());
        return this.baseMapper.pageList(page, queryParams);
    }

    @Override
    public TourSchemeVO getOneById(Long id) {
        TourScheme scheme = getById(id);
        //获取督导领域名称
        SuperviseDomain domain = superviseDomainService.getById(scheme.getSuperviseDomainId());
        TourSchemeVO tourSchemeVO = tourSchemeConverter.entity2VO(scheme);
        tourSchemeVO.setSuperviseDomainName(domain.getSuperviseDomainName());
        return tourSchemeVO;
    }

    @Override
    public boolean deleteOne(Long id) {
        //根据方案ID，删除相应方案
        return removeById(id);
    }
}




