package com.ctf.admin.converter;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ctf.admin.pojo.entity.SysDictType;
import com.ctf.admin.pojo.form.DictTypeForm;
import com.ctf.admin.pojo.vo.dict.DictTypePageVO;
import org.mapstruct.Mapper;


/**
 * 字典类型对象转换器
 *
 * @ClassName DictTypeConverter
 * @Description
 * @Author H.m
 * @date 2022/8/5 10:30
 * @Version 1.0
 **/
@Mapper(componentModel = "spring")
public interface DictTypeConverter {

    Page<DictTypePageVO> entity2Page(Page<SysDictType> page);

    DictTypeForm entity2Form(SysDictType entity);

    SysDictType form2Entity(DictTypeForm entity);
}
