package com.zdf.auth.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import com.zdf.common.utils.CurrentUserUtil;
import com.zdf.auth.model.po.BCountry;
import com.zdf.auth.mapper.BCountryMapper;
import com.zdf.auth.service.IBCountryService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * 国家表 服务实现类
 *
 * @author jayud
 * @since 2022-04-22
 */
@Slf4j
@Service
public class BCountryServiceImpl extends ServiceImpl<BCountryMapper, BCountry> implements IBCountryService {


    @Autowired
    private BCountryMapper bCountryMapper;

    @Override
    public IPage<BCountry> selectPage(BCountry bCountry,
                                        Integer currentPage,
                                        Integer pageSize,
                                        HttpServletRequest req){

        Page<BCountry> page=new Page<BCountry>(currentPage,pageSize);
        IPage<BCountry> pageList= bCountryMapper.pageList(page, bCountry);
        return pageList;
    }

    @Override
    public List<BCountry> selectList(BCountry bCountry){
        return bCountryMapper.list(bCountry);
    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public void phyDelById(Long id){
        bCountryMapper.phyDelById(id);
    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public void logicDel(Long id){
        bCountryMapper.logicDel(id,CurrentUserUtil.getUsername());
    }


    @Override
    public List<LinkedHashMap<String, Object>> queryBCountryForExcel(Map<String, Object> paramMap) {
        return this.baseMapper.queryBCountryForExcel(paramMap);
    }

}
