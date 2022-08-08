package com.ctf.generator.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ctf.generator.entity.TableFieldEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 表
 *
 * @author H.m
 */
@Mapper
public interface TableFieldMapper extends BaseMapper<TableFieldEntity> {

    List<TableFieldEntity> getByTableName(String tableName);

    void deleteByTableName(String tableName);

    void deleteBatchTableIds(Long[] tableIds);
}
