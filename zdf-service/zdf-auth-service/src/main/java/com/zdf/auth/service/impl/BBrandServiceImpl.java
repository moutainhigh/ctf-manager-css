package com.zdf.auth.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import com.zdf.common.utils.CurrentUserUtil;
import com.zdf.auth.model.po.BBrand;
import com.zdf.auth.mapper.BBrandMapper;
import com.zdf.auth.service.IBBrandService;
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
 * 品牌库表 服务实现类
 *
 * @author jayud
 * @since 2022-04-22
 */
@Slf4j
@Service
public class BBrandServiceImpl extends ServiceImpl<BBrandMapper, BBrand> implements IBBrandService {


    @Autowired
    private BBrandMapper bBrandMapper;

    @Override
    public IPage<BBrand> selectPage(BBrand bBrand,
                                        Integer currentPage,
                                        Integer pageSize,
                                        HttpServletRequest req){

        Page<BBrand> page=new Page<BBrand>(currentPage,pageSize);
        IPage<BBrand> pageList= bBrandMapper.pageList(page, bBrand);
        return pageList;
    }

    @Override
    public List<BBrand> selectList(BBrand bBrand){
        return bBrandMapper.list(bBrand);
    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public void phyDelById(Long id){
        bBrandMapper.phyDelById(id);
    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public void logicDel(Long id){
        bBrandMapper.logicDel(id,CurrentUserUtil.getUsername());
    }


    @Override
    public List<LinkedHashMap<String, Object>> queryBBrandForExcel(Map<String, Object> paramMap) {
        return this.baseMapper.queryBBrandForExcel(paramMap);
    }

}
