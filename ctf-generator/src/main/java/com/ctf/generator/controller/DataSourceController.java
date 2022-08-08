package com.ctf.generator.controller;

import lombok.AllArgsConstructor;
import com.ctf.generator.common.page.PageResult;
import com.ctf.generator.common.query.Query;
import com.ctf.generator.common.utils.Result;
import com.ctf.generator.config.DataSourceInfo;
import com.ctf.generator.entity.DataSourceEntity;
import com.ctf.generator.service.DataSourceService;
import com.ctf.generator.utils.DbUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

/**
 * 数据源管理
 *
 * @author H.m
 */
@RestController
@RequestMapping("gen/datasource")
@AllArgsConstructor
public class DataSourceController {
    private final DataSourceService datasourceService;

    @GetMapping("page")
    public Result<PageResult<DataSourceEntity>> page(Query query){
        PageResult<DataSourceEntity> page = datasourceService.page(query);

        return Result.ok(page);
    }

    @GetMapping("list")
    public Result<List<DataSourceEntity>> list(){
        List<DataSourceEntity> list = datasourceService.getList();

        return Result.ok(list);
    }

    @GetMapping("{id}")
    public Result<DataSourceEntity> get(@PathVariable("id") Long id){
        DataSourceEntity data = datasourceService.getById(id);

        return Result.ok(data);
    }

    @GetMapping("test/{id}")
    public Result<String> test(@PathVariable("id") Long id){
        try {
            DataSourceEntity entity = datasourceService.getById(id);

            DbUtils.getConnection(new DataSourceInfo(entity));
            return Result.ok("连接成功");
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error("连接失败，请检查配置信息");
        }
    }

    @PostMapping
    public Result<String> save(@RequestBody DataSourceEntity entity){
        datasourceService.save(entity);

        return Result.ok();
    }

    @PutMapping
    public Result<String> update(@RequestBody DataSourceEntity entity){
        datasourceService.updateById(entity);

        return Result.ok();
    }

    @DeleteMapping
    public Result<String> delete(@RequestBody Long[] ids){
        datasourceService.removeBatchByIds(Arrays.asList(ids));

        return Result.ok();
    }
}
