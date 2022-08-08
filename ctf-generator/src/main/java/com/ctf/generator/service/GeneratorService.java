package com.ctf.generator.service;

import com.ctf.generator.config.DataSourceInfo;
import com.ctf.generator.entity.TableFieldEntity;
import com.ctf.generator.entity.TableInfoEntity;

import java.util.List;

/**
 * 代码生成
 *
 * @author H.m
 */
public interface GeneratorService {

    DataSourceInfo getDataSourceInfo(Long datasourceId);

    void datasourceTable(TableInfoEntity tableInfo);

    void updateTableField(Long tableId, List<TableFieldEntity> tableFieldList);

    void generatorCode(TableInfoEntity tableInfo) throws Exception;
}
