package com.ctf.generator.service.impl;

import com.ctf.generator.common.service.impl.BaseServiceImpl;
import com.ctf.generator.mapper.TableFieldMapper;
import com.ctf.generator.entity.TableFieldEntity;
import com.ctf.generator.service.TableFieldService;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * 表
 *
 * @author H.m
 */
@Service
public class TableFieldServiceImpl extends BaseServiceImpl<TableFieldMapper, TableFieldEntity> implements TableFieldService {

    @Override
    public List<TableFieldEntity> getByTableName(String tableName) {
        return baseMapper.getByTableName(tableName);
    }

    @Override
    public void deleteByTableName(String tableName) {
        baseMapper.deleteByTableName(tableName);
    }

    @Override
    public void deleteBatchTableIds(Long[] tableIds) {
        baseMapper.deleteBatchTableIds(tableIds);
    }

}
