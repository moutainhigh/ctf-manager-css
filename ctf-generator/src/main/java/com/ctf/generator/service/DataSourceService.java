package com.ctf.generator.service;

import com.ctf.generator.common.page.PageResult;
import com.ctf.generator.common.query.Query;
import com.ctf.generator.common.service.BaseService;
import com.ctf.generator.entity.DataSourceEntity;

import java.util.List;

/**
 * 数据源管理
 *
 * @author H.m
 */
public interface DataSourceService extends BaseService<DataSourceEntity> {

    PageResult<DataSourceEntity> page(Query query);

    List<DataSourceEntity> getList();
}
