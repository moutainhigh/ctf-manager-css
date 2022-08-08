package com.ctf.generator.controller;

import lombok.AllArgsConstructor;
import com.ctf.generator.common.page.PageResult;
import com.ctf.generator.common.query.Query;
import com.ctf.generator.common.utils.Result;
import com.ctf.generator.config.DataSourceInfo;
import com.ctf.generator.entity.TableFieldEntity;
import com.ctf.generator.entity.TableInfoEntity;
import com.ctf.generator.service.GeneratorService;
import com.ctf.generator.service.TableFieldService;
import com.ctf.generator.service.TableInfoService;
import com.ctf.generator.utils.DbUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 代码生成
 *
 * @author H.m
 */
@RestController
@RequestMapping("gen")
@AllArgsConstructor
public class GeneratorController {
    private final GeneratorService generatorService;
    private final TableInfoService tableInfoService;
    private final TableFieldService tableFieldService;

    @GetMapping("table/page")
    public Result<PageResult<TableInfoEntity>> pageTable(Query query){
        PageResult<TableInfoEntity> page = tableInfoService.page(query);

        return Result.ok(page);
    }

    @GetMapping("table/{id}")
    public Result<TableInfoEntity> getTable(@PathVariable("id") Long id){
        TableInfoEntity table = tableInfoService.getById(id);

        List<TableFieldEntity> fieldList = tableFieldService.getByTableName(table.getTableName());
        table.setFields(fieldList);

        return Result.ok(table);
    }

    @PutMapping("table")
    public Result<String> updateTable(@RequestBody TableInfoEntity tableInfo){
        tableInfoService.updateById(tableInfo);

        return Result.ok();
    }

    @DeleteMapping("table")
    public Result<String> deleteTable(@RequestBody Long[] ids){
        tableInfoService.deleteBatchIds(ids);

        return Result.ok();
    }

    /**
     * 获取数据源中所有表
     */
    @GetMapping("datasource/table/list/{id}")
    public Result<List<TableInfoEntity>> getDataSourceTableList(@PathVariable("id") Long id){
        try {
            //初始化配置信息
            DataSourceInfo info = generatorService.getDataSourceInfo(id);
            List<TableInfoEntity> tableInfoList = DbUtils.getTablesInfoList(info);

            return Result.ok(tableInfoList);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error("数据源配置错误，请检查数据源配置！");
        }
    }

    /**
     * 导入数据源中的表
     */
    @PostMapping("datasource/table")
    public Result<String> datasourceTable(@RequestBody TableInfoEntity tableInfo) {
        generatorService.datasourceTable(tableInfo);

        return Result.ok();
    }

    /**
     * 更新列数据
     */
    @PutMapping("table/field/{tableId}")
    public Result<String> updateTableField(@PathVariable("tableId") Long tableId, @RequestBody List<TableFieldEntity> tableFieldList) {
        generatorService.updateTableField(tableId, tableFieldList);

        return Result.ok();
    }

    /**
     * 生成代码
     */
    @PostMapping("generator")
    public Result<String> generator(@RequestBody TableInfoEntity tableInfo) throws Exception {
        //保存表信息
        tableInfoService.updateById(tableInfo);

        //生成代码
        generatorService.generatorCode(tableInfo);

        return Result.ok();
    }
}
