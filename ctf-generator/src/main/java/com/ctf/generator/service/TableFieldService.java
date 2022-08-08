package com.ctf.generator.service;

import com.ctf.generator.common.service.BaseService;
import com.ctf.generator.entity.TableFieldEntity;

import java.util.List;

/**
 * 列
 *
 * @author H.m
 */
public interface TableFieldService extends BaseService<TableFieldEntity> {

    List<TableFieldEntity> getByTableName(String tableName);

    void deleteByTableName(String tableName);

    void deleteBatchTableIds(Long[] tableIds);
}
