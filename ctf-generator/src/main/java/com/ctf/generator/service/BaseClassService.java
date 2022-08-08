package com.ctf.generator.service;

import com.ctf.generator.common.page.PageResult;
import com.ctf.generator.common.query.Query;
import com.ctf.generator.common.service.BaseService;
import com.ctf.generator.entity.BaseClassEntity;

import java.util.List;

/**
 * 基类管理
 *
 * @author H.m
 */
public interface BaseClassService extends BaseService<BaseClassEntity> {

    PageResult<BaseClassEntity> page(Query query);

    List<BaseClassEntity> getList();
}
