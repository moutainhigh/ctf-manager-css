package com.ctf.pms.converter;

import com.ctf.pms.pojo.entity.PmsSpuAttribute;
import com.ctf.pms.pojo.form.PmsSpuAttributeForm;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

/**
 * 商品属性对象转换器
 *
 * @author H.m
 * @date 2022/6/11
 */
@Mapper(componentModel = "spring")
public interface SpuAttributeConverter {

    @Mappings({
            @Mapping(target = "id",ignore = true)
    })
    PmsSpuAttribute form2Entity(PmsSpuAttributeForm form);

}
