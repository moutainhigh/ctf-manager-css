package com.ctf.generator.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.ctf.generator.common.page.PageResult;
import com.ctf.generator.common.query.Query;
import com.ctf.generator.common.service.impl.BaseServiceImpl;
import com.ctf.generator.mapper.DataSourceMapper;
import com.ctf.generator.entity.DataSourceEntity;
import com.ctf.generator.service.DataSourceService;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * 数据源管理
 *
 * @author H.m
 */
@Service
public class DataSourceServiceImpl extends BaseServiceImpl<DataSourceMapper, DataSourceEntity> implements DataSourceService {

    @Override
    public PageResult<DataSourceEntity> page(Query query) {
        IPage<DataSourceEntity> page = baseMapper.selectPage(
            getPage(query),
            getWrapper(query)
        );
        return new PageResult<>(page.getRecords(), page.getTotal());
    }

    @Override
    public List<DataSourceEntity> getList() {
        return baseMapper.selectList(Wrappers.emptyWrapper());
    }

}
