package com.ctf.generator.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ctf.generator.entity.TableInfoEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * 列
 *
 * @author H.m
 */
@Mapper
public interface TableInfoMapper extends BaseMapper<TableInfoEntity> {
    TableInfoEntity getByTableName(String tableName);

    void deleteByTableName(String tableName);
}
