package com.ctf.auth.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.ctf.auth.model.po.SysDict;
import com.ctf.common.exception.JayudBizException;
import com.ctf.common.utils.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.ctf.common.constant.SysTips;
import com.ctf.common.BaseResult;
import com.ctf.auth.service.ISysDictItemService;
import com.ctf.auth.model.po.SysDictItem;

import java.util.*;
import java.util.stream.Collectors;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

/**
 * 字典项 控制类
 *
 * @author jayud
 * @since 2022-02-23
 */
@Slf4j
@Api(tags = "字典项")
@RestController
@RequestMapping("/sysDictItem")
public class SysDictItemController {


    @Autowired
    public ISysDictItemService sysDictItemService;


    /**
     * @description 分页查询
     * @author jayud
     * @date 2022-02-23
     * @param: sysDictItem
     * @param: currentPage
     * @param: pageSize
     * @param: req
     * @return: com.ctf.common.BaseResult<com.baomidou.mybatisplus.core.metadata.IPage < com.ctf.auth.model.po.SysDictItem>>
     **/
    @ApiOperation("分页查询数据")
    @GetMapping("/selectPage")
    public BaseResult<IPage<SysDictItem>> selectPage(SysDictItem sysDictItem,
                                                     @RequestParam(name = "currentPage", defaultValue = "1") Integer currentPage,
                                                     @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize,
                                                     HttpServletRequest req) {


        return BaseResult.ok(sysDictItemService.selectPage(sysDictItem, currentPage, pageSize, req));
    }


    /**
     * @description 列表查询数据
     * @author jayud
     * @date 2022-02-23
     * @param: sysDictItem
     * @param: req
     * @return: com.ctf.common.BaseResult<java.util.List < com.ctf.auth.model.po.SysDictItem>>
     **/
    @ApiOperation("列表查询数据")
    @GetMapping("/selectList")
    public BaseResult<List<SysDictItem>> selectList(SysDictItem sysDictItem,
                                                    HttpServletRequest req) {
        return BaseResult.ok(sysDictItemService.selectList(sysDictItem));
    }


    /**
     * @description 新增
     * @author jayud
     * @date 2022-02-23
     * @param: sysDictItem
     * @return: com.ctf.common.BaseResult
     **/
    @ApiOperation("新增")
    @PostMapping("/add")
    @CacheEvict(value="sys:cache:dict", allEntries=true)
    public BaseResult add(@Valid @RequestBody SysDictItem sysDictItem) {
        sysDictItem.setIsEdit(null);
        if (StringUtils.isEmpty(sysDictItem.getItemText()) || StringUtils.isEmpty(sysDictItem.getItemValue())) {
            return BaseResult.error("请填写字典类别/编码");
        }
        if (sysDictItem.getSortOrder() != null) {
            if (sysDictItem.getSortOrder() < 0) {
                return BaseResult.error("排序请输入大于0整数");
            }
            SysDictItem tmp = sysDictItemService.getOne(new QueryWrapper<>(new SysDictItem().setDictId(sysDictItem.getDictId()).setSortOrder(sysDictItem.getSortOrder()).setIsDeleted(false)));
            if (tmp != null && !tmp.getId().equals(sysDictItem.getId())) {
                return BaseResult.error("该排列数值已存在");
            }
            tmp = sysDictItemService.getOne(new QueryWrapper<>(new SysDictItem().setDictId(sysDictItem.getDictId()).setItemText(sysDictItem.getItemText()).setIsDeleted(false)));
            if (tmp != null && !tmp.getId().equals(sysDictItem.getId())) {
                return BaseResult.error("该字典名称已存在");
            }
            tmp = sysDictItemService.getOne(new QueryWrapper<>(new SysDictItem().setDictId(sysDictItem.getDictId()).setItemValue(sysDictItem.getItemValue()).setIsDeleted(false)));
            if (tmp != null && !tmp.getId().equals(sysDictItem.getId())) {
                return BaseResult.error("该字典值已存在");
            }

        }
//        this.sysDictItemService.checkUnique(sysDictItem);
        sysDictItemService.save(sysDictItem);
        return BaseResult.ok(SysTips.ADD_SUCCESS);
    }


    /**
     * @description 编辑
     * @author jayud
     * @date 2022-02-23
     * @param: sysDictItem
     * @return: com.ctf.common.BaseResult
     **/
    @ApiOperation("编辑")
    @PostMapping("/edit")
    @CacheEvict(value="sys:cache:dict", allEntries=true)
    public BaseResult edit(@Valid @RequestBody SysDictItem sysDictItem) {
        sysDictItem.setIsEdit(null);
        if (StringUtils.isEmpty(sysDictItem.getItemText()) || StringUtils.isEmpty(sysDictItem.getItemValue())) {
            return BaseResult.error("请填写字典类别/编码");
        }
        if (sysDictItem.getSortOrder() != null) {
            if (sysDictItem.getSortOrder() < 0) {
                return BaseResult.error("排序请输入大于0整数");
            }
            SysDictItem tmp = sysDictItemService.getOne(new QueryWrapper<>(new SysDictItem().setDictId(sysDictItem.getDictId()).setSortOrder(sysDictItem.getSortOrder()).setIsDeleted(false)));
            if (tmp != null && !tmp.getId().equals(sysDictItem.getId())) {
                return BaseResult.error("该排列数值已存在");
            }
            tmp = sysDictItemService.getOne(new QueryWrapper<>(new SysDictItem().setDictId(sysDictItem.getDictId()).setItemText(sysDictItem.getItemText()).setIsDeleted(false)));
            if (tmp != null && !tmp.getId().equals(sysDictItem.getId())) {
                return BaseResult.error("该字典名称已存在");
            }

            SysDictItem item = sysDictItemService.getById(sysDictItem.getId());
            if (!item.getIsEdit()) {
                return BaseResult.error("数据字典不能修改");
            }
        }
        SysDictItem tmp = sysDictItemService.getOne(new QueryWrapper<>(new SysDictItem().setDictId(sysDictItem.getDictId()).setItemText(sysDictItem.getItemText()).setIsDeleted(false)));
        SysDictItem tmp1 = sysDictItemService.getOne(new QueryWrapper<>(new SysDictItem().setDictId(sysDictItem.getDictId()).setItemValue(sysDictItem.getItemValue()).setIsDeleted(false)));
        if(null != tmp){
            if(null == sysDictItem.getId()){
                return BaseResult.error("该字典名称已存在");
            }
            if (!tmp.getId().equals(sysDictItem.getId())) {
                return BaseResult.error("该字典名称已存在");
            }
        }
        if(null != tmp1){
            if(null == sysDictItem.getId()){
                return BaseResult.error("该字典值已存在");
            }
            if (!tmp1.getId().equals(sysDictItem.getId())) {
                return BaseResult.error("该字典值已存在");
            }
        }


        this.sysDictItemService.update(null,
                Wrappers.<SysDictItem>lambdaUpdate()
                        .set(SysDictItem::getItemText, sysDictItem.getItemText())
                        .set(SysDictItem::getItemValue, sysDictItem.getItemValue())
                        .set(SysDictItem::getSortOrder, sysDictItem.getSortOrder())
                        .set(SysDictItem::getCustomOne,sysDictItem.getCustomOne())
                        .set(SysDictItem::getCustomTwo,sysDictItem.getCustomTwo())
                        .set(SysDictItem::getCustomThree,sysDictItem.getCustomThree())
                        .set(SysDictItem::getCustomFour,sysDictItem.getCustomFour())
                        .set(SysDictItem::getCustomFive,sysDictItem.getCustomFive())
                        .eq(SysDictItem::getId, sysDictItem.getId()));
//        sysDictItemService.updateById(sysDictItem);
        return BaseResult.ok(SysTips.EDIT_SUCCESS);
    }


    /**
     * @description 物理删除
     * @author jayud
     * @date 2022-02-23
     * @param: id
     * @return: com.ctf.common.BaseResult
     **/
//    @ApiOperation("物理删除")
//    @ApiImplicitParam(name = "id", value = "主键id", dataType = "Long", required = true)
//    @GetMapping("/phyDel")
//    public BaseResult phyDel(@RequestParam Long id) {
//        sysDictItemService.phyDelById(id);
//        return BaseResult.ok(SysTips.DEL_SUCCESS);
//    }

    /**
     * @description 逻辑删除
     * @author jayud
     * @date 2022-02-23
     * @param: id
     * @return: com.ctf.common.BaseResult
     **/
    @ApiOperation("逻辑删除")
    @ApiImplicitParam(name = "id", value = "主键id", dataType = "Long", required = true)
    @GetMapping("/logicDel")
    public BaseResult logicDel(@RequestParam Long id) {
        sysDictItemService.logicDel(id);
        return BaseResult.ok(SysTips.DEL_SUCCESS);
    }

    /**
     * @description 批量逻辑删除
     * @author jayud
     * @date 2022-02-21
     * @param: id
     * @return: com.ctf.common.BaseResult
     **/
    @ApiOperation("批量逻辑删除")
    @PostMapping("/batchLogicDel")
    public BaseResult batchLogicDel(@RequestBody List<SysDictItem> sysDictItems) {
        List<Long> ids = sysDictItems.stream().map(e -> e.getId()).collect(Collectors.toList());
        List<SysDictItem> tmps = new ArrayList<>();
        for (Long id : ids) {
            SysDictItem tmp = new SysDictItem();
            tmp.setIsDeleted(true).setId(id);
            tmps.add(tmp);
        }
        this.sysDictItemService.updateBatchById(tmps);
        return BaseResult.ok(SysTips.DEL_SUCCESS);
    }


    /**
     * @description 根据id查询
     * @author jayud
     * @date 2022-02-23
     * @param: id
     * @return: com.ctf.common.BaseResult<com.ctf.auth.model.po.SysDictItem>
     **/
    @ApiOperation("根据id查询")
    @ApiImplicitParam(name = "id", value = "主键id", dataType = "int", required = true)
    @GetMapping(value = "/queryById")
    public BaseResult<SysDictItem> queryById(@RequestParam(name = "id", required = true) int id) {
        SysDictItem sysDictItem = sysDictItemService.getById(id);
        return BaseResult.ok(sysDictItem);
    }

    @ApiOperation("根据字典编码查询子项")
    @ApiImplicitParam(name = "dictCode", value = "字典编码", dataType = "String", required = true)
    @GetMapping("/selectItemByDictCode")
    public BaseResult<List<SysDictItem>> selectItemByDictCode(@RequestParam("dictCode") String dictCode,
                                                              HttpServletRequest req) {
        return BaseResult.ok(sysDictItemService.selectItemByDictCode(dictCode));
    }

    @ApiOperation("根据字典编码查询子项")
    @PostMapping(value = "/api/selectItemByDictCode")
    public BaseResult selectItemByDictCodeFeign(@RequestParam("dictCode") String dictCode,
                                                HttpServletRequest req) {
        List<SysDictItem> sysDictItems = sysDictItemService.selectItemByDictCode(dictCode);
        System.out.println("字典表：" + sysDictItems);
        return BaseResult.ok(sysDictItems);
    }

}
