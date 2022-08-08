package com.ctf.generator.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.ctf.generator.common.page.PageResult;
import com.ctf.generator.common.query.Query;
import com.ctf.generator.common.service.impl.BaseServiceImpl;
import com.ctf.generator.mapper.FieldTypeMapper;
import com.ctf.generator.entity.FieldTypeEntity;
import com.ctf.generator.service.FieldTypeService;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;


/**
 * 字段类型管理
 *
 * @author H.m
 */
@Service
public class FieldTypeServiceImpl extends BaseServiceImpl<FieldTypeMapper, FieldTypeEntity> implements FieldTypeService {

    @Override
    public PageResult<FieldTypeEntity> page(Query query) {
        IPage<FieldTypeEntity> page = baseMapper.selectPage(
            getPage(query),
            getWrapper(query)
        );
        return new PageResult<>(page.getRecords(), page.getTotal());
    }

    @Override
    public Map<String, FieldTypeEntity> getMap() {
        List<FieldTypeEntity> list = baseMapper.selectList(null);
        Map<String, FieldTypeEntity> map = new LinkedHashMap<>(list.size());
        for(FieldTypeEntity entity : list){
            map.put(entity.getColumnType().toLowerCase(), entity);
        }
        return map;
    }

    @Override
    public Set<String> getPackageListByTableId(Long tableId) {
        return baseMapper.getPackageListByTableId(tableId);
    }

    @Override
    public Set<String> getList() {
        return baseMapper.list();
    }

}
