package com.ctf.generator.service;

import com.ctf.generator.common.page.PageResult;
import com.ctf.generator.common.query.Query;
import com.ctf.generator.common.service.BaseService;
import com.ctf.generator.entity.TableInfoEntity;

/**
 * 表
 *
 * @author H.m
 */
public interface TableInfoService extends BaseService<TableInfoEntity> {

    PageResult<TableInfoEntity> page(Query query);

    TableInfoEntity getByTableName(String tableName);

    void deleteByTableName(String tableName);

    void deleteBatchIds(Long[] ids);
}
