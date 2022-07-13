package com.ctf.auth.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import com.ctf.common.utils.CurrentUserUtil;
import com.ctf.auth.model.po.BBanks;
import com.ctf.auth.mapper.BBanksMapper;
import com.ctf.auth.service.IBBanksService;
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
 * 公司银行账户 服务实现类
 *
 * @author jayud
 * @since 2022-04-22
 */
@Slf4j
@Service
public class BBanksServiceImpl extends ServiceImpl<BBanksMapper, BBanks> implements IBBanksService {


    @Autowired
    private BBanksMapper bBanksMapper;

    @Override
    public IPage<BBanks> selectPage(BBanks bBanks,
                                        Integer currentPage,
                                        Integer pageSize,
                                        HttpServletRequest req){

        Page<BBanks> page=new Page<BBanks>(currentPage,pageSize);
        IPage<BBanks> pageList= bBanksMapper.pageList(page, bBanks);
        return pageList;
    }

    @Override
    public List<BBanks> selectList(BBanks bBanks){
        return bBanksMapper.list(bBanks);
    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public void phyDelById(Long id){
        bBanksMapper.phyDelById(id);
    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public void logicDel(Long id){
        bBanksMapper.logicDel(id,CurrentUserUtil.getUsername());
    }


    @Override
    public List<LinkedHashMap<String, Object>> queryBBanksForExcel(Map<String, Object> paramMap) {
        return this.baseMapper.queryBBanksForExcel(paramMap);
    }

}
