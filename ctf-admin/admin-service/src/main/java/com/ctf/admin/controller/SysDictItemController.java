package com.ctf.admin.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ctf.admin.pojo.form.DictItemForm;
import com.ctf.admin.pojo.query.DictItemPageQuery;
import com.ctf.admin.pojo.vo.dict.DictItemPageVO;
import com.ctf.admin.service.SysDictItemService;
import com.ctf.common.result.PageResult;
import com.ctf.common.result.Result;
import com.ctf.common.web.domain.Option;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * 数据字典
 *
 * @ClassName SysDictItemController
 * @Description
 * @Author H.m
 * @date 2022/8/5 10:30
 * @Version 1.0
 **/

@Api(tags = "字典数据项接口")
@RestController
@RequestMapping("/api/v1/dict-items")
@RequiredArgsConstructor
public class SysDictItemController {

    private final SysDictItemService dictItemService;

    @ApiOperation(value = "字典数据项分页列表")
    @GetMapping
    public PageResult<DictItemPageVO> listDictItemPages(DictItemPageQuery queryParams) {
        Page<DictItemPageVO> result = dictItemService.listDictItemPages(queryParams);
        return PageResult.success(result);
    }

    @ApiOperation(value = "字典数据项表单")
    @GetMapping("/{id}/form_data")
    public Result<DictItemForm> getDictItemFormData(@ApiParam("字典ID") @PathVariable Long id) {
        DictItemForm formData = dictItemService.getDictItemFormData(id);
        return Result.success(formData);
    }

    @ApiOperation(value = "新增字典数据项")
    @PostMapping
    public Result saveDictItem(@RequestBody DictItemForm DictItemForm) {
        boolean result = dictItemService.saveDictItem(DictItemForm);
        return Result.judge(result);
    }

    @ApiOperation(value = "修改字典数据项")
    @PutMapping("/{id}")
    public Result updateDictItem(@PathVariable Long id, @RequestBody DictItemForm DictItemForm) {
        boolean status = dictItemService.updateDictItem(id, DictItemForm);
        return Result.judge(status);
    }

    @ApiOperation(value = "删除字典")
    @DeleteMapping("/{ids}")
    public Result deleteDictItems(@ApiParam("字典ID，多个以英文逗号(,)分割") @PathVariable String ids) {
        boolean result = dictItemService.deleteDictItems(ids);
        return Result.judge(result);
    }

    @ApiOperation(value = "根据字典类型编码获取字典数据项")
    @GetMapping("/select_list")
    public Result<List<Option>> getDictItemsByTypeCode(@ApiParam("字典类型编码") @RequestParam String typeCode) {
        List<Option> list = dictItemService.listDictItemsByTypeCode(typeCode);
        return Result.success(list);
    }

}
