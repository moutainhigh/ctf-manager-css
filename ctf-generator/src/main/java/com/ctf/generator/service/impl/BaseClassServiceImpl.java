
package com.ctf.generator.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.ctf.generator.common.page.PageResult;
import com.ctf.generator.common.query.Query;
import com.ctf.generator.common.service.impl.BaseServiceImpl;
import com.ctf.generator.mapper.BaseClassMapper;
import com.ctf.generator.entity.BaseClassEntity;
import com.ctf.generator.service.BaseClassService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 基类管理
 *
 * @author H.m
 */
@Service
public class BaseClassServiceImpl extends BaseServiceImpl<BaseClassMapper, BaseClassEntity> implements BaseClassService {

    @Override
    public PageResult<BaseClassEntity> page(Query query) {
        IPage<BaseClassEntity> page = baseMapper.selectPage(
            getPage(query), getWrapper(query)
        );

        return new PageResult<>(page.getRecords(), page.getTotal());
    }

    @Override
    public List<BaseClassEntity> getList() {
        return baseMapper.selectList(null);
    }
}
