package com.zdf.auth.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import com.zdf.common.utils.CurrentUserUtil;
import com.zdf.auth.model.po.SystemConfig;
import com.zdf.auth.mapper.SystemConfigMapper;
import com.zdf.auth.service.ISystemConfigService;
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
 * 系统配置表 服务实现类
 *
 * @author jayud
 * @since 2022-03-23
 */
@Slf4j
@Service
public class SystemConfigServiceImpl extends ServiceImpl<SystemConfigMapper, SystemConfig> implements ISystemConfigService {


    @Autowired
    private SystemConfigMapper systemConfigMapper;

    @Override
    public IPage<SystemConfig> selectPage(SystemConfig systemConfig,
                                        Integer currentPage,
                                        Integer pageSize,
                                        HttpServletRequest req){

        Page<SystemConfig> page=new Page<SystemConfig>(currentPage,pageSize);
        IPage<SystemConfig> pageList= systemConfigMapper.pageList(page, systemConfig);
        return pageList;
    }

    @Override
    public List<SystemConfig> selectList(SystemConfig systemConfig){
        return systemConfigMapper.list(systemConfig);
    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public void phyDelById(Long id){
        systemConfigMapper.phyDelById(id);
    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public void logicDel(Long id){
        systemConfigMapper.logicDel(id,CurrentUserUtil.getUsername());
    }


    @Override
    public List<LinkedHashMap<String, Object>> querySystemConfigForExcel(Map<String, Object> paramMap) {
        return this.baseMapper.querySystemConfigForExcel(paramMap);
    }

    @Override
    public Boolean saveOrUpdateSystemConfig(List<SystemConfig> systemConfig) {
        for (SystemConfig config : systemConfig) {
            if(null != config.getId()){
                config.setUpdateBy(CurrentUserUtil.getUsername());
                config.setUpdateTime(new Date());
            }else{
                config.setCreateBy(CurrentUserUtil.getUsername());
                config.setCreateTime(new Date());
            }
        }

        boolean result = this.saveOrUpdateBatch(systemConfig);
        return result;
    }

    @Override
    public SystemConfig getSystemConfigByConfigCode(String configCode) {
        QueryWrapper<SystemConfig> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(SystemConfig::getConfigCode,configCode).eq(SystemConfig::getIsDeleted,0);
        return this.getOne(queryWrapper);
    }

}
