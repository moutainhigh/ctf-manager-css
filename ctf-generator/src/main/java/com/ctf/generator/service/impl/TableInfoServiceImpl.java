package com.ctf.generator.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import lombok.AllArgsConstructor;
import com.ctf.generator.common.page.PageResult;
import com.ctf.generator.common.query.Query;
import com.ctf.generator.common.service.impl.BaseServiceImpl;
import com.ctf.generator.mapper.TableInfoMapper;
import com.ctf.generator.entity.TableInfoEntity;
import com.ctf.generator.service.TableFieldService;
import com.ctf.generator.service.TableInfoService;
import org.springframework.stereotype.Service;

import java.util.Arrays;


/**
 * 表
 *
 * @author H.m
 */
@Service
@AllArgsConstructor
public class TableInfoServiceImpl extends BaseServiceImpl<TableInfoMapper, TableInfoEntity> implements TableInfoService {
    private final TableFieldService tableFieldService;

    @Override
    public PageResult<TableInfoEntity> page(Query query) {
        IPage<TableInfoEntity> page = baseMapper.selectPage(
            getPage(query),
            getWrapper(query)
        );
        return new PageResult<>(page.getRecords(), page.getTotal());
    }

    @Override
    public TableInfoEntity getByTableName(String tableName) {
        return baseMapper.getByTableName(tableName);
    }

    @Override
    public void deleteByTableName(String tableName) {
        baseMapper.deleteByTableName(tableName);
    }

    @Override
    public void deleteBatchIds(Long[] ids) {
        //删除表
        baseMapper.deleteBatchIds(Arrays.asList(ids));

        //删除列
        tableFieldService.deleteBatchTableIds(ids);
    }
}
