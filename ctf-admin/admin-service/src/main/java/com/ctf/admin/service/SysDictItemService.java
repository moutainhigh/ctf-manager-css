package com.ctf.admin.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ctf.admin.pojo.entity.SysDictItem;
import com.ctf.admin.pojo.form.DictItemForm;
import com.ctf.admin.pojo.query.DictItemPageQuery;
import com.ctf.admin.pojo.vo.dict.DictItemPageVO;
import com.ctf.common.web.domain.Option;

import java.util.List;


/**
 * 字典数据项业务接口层
 *
 * @author H.m
 * @date 2022/8/5 10:30
 */
public interface SysDictItemService extends IService<SysDictItem> {

    /**
     * 字典数据项分页列表
     *
     * @param queryParams
     * @return
     */
    Page<DictItemPageVO> listDictItemPages(DictItemPageQuery queryParams);

    /**
     * 字典数据项表单详情
     *
     * @param id 字典数据项ID
     * @return
     */
    DictItemForm getDictItemFormData(Long id);

    /**
     * 新增字典数据项
     *
     * @param dictItemForm 字典数据项表单
     * @return
     */
    boolean saveDictItem(DictItemForm dictItemForm);

    /**
     * 修改字典数据项
     *
     * @param id           字典数据项ID
     * @param dictItemForm 字典数据项表单
     * @return
     */
    boolean updateDictItem(Long id, DictItemForm dictItemForm);

    /**
     * 删除字典数据项
     *
     * @param idsStr 字典数据项ID，多个以英文逗号(,)分割
     * @return
     */
    boolean deleteDictItems(String idsStr);

    /**
     * 根据字典类型编码获取字典数据项
     *
     * @param typeCode 字典类型编码
     * @return
     */
    List<Option> listDictItemsByTypeCode(String typeCode);
}
