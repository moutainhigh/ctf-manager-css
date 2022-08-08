package com.ctf.admin.converter;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ctf.admin.pojo.entity.SysDictItem;
import com.ctf.admin.pojo.form.DictItemForm;
import com.ctf.admin.pojo.vo.dict.DictItemPageVO;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;

/**
 * 字典数据项对象转换器
 *
 * @ClassName DictItemConverter
 * @Description
 * @Author H.m
 * @date 2022/8/5 10:30
 * @Version 1.0
 **/
@Mapper(componentModel = "spring")
public interface DictItemConverter {

    Page<DictItemPageVO> entity2Page(Page<SysDictItem> page);

    DictItemForm entity2Form(SysDictItem entity);

    @InheritInverseConfiguration(name = "entity2Form")
    SysDictItem form2Entity(DictItemForm entity);
}
