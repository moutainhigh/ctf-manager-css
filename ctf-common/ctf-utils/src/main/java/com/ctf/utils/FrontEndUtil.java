package com.ctf.utils;

import cn.hutool.core.util.StrUtil;
import io.swagger.annotations.ApiModelProperty;

import java.lang.reflect.Field;
import java.util.LinkedList;

public class FrontEndUtil {

    public static <T> LinkedList<T> getSwagger2FrontEndVo(Class<?> clazz){
        LinkedList<T> linkedList = new LinkedList<>();
        Field[] declaredFields = clazz.getDeclaredFields();
        for(Field field : declaredFields){
            ApiModelProperty apiModelProperty = field.getAnnotation(ApiModelProperty.class);
            if(apiModelProperty==null){
                continue;
            }
            String name = apiModelProperty.name();
            String value = apiModelProperty.value();
            boolean hidden = apiModelProperty.hidden();
            String notes = apiModelProperty.notes();

            FrontEndVo frontEndVo = new FrontEndVo();
            frontEndVo.setLabel((StrUtil.isNotEmpty(name)?name:field.getName()));
            frontEndVo.setProp((StrUtil.isNotEmpty(value)?value:field.getName()));
            frontEndVo.setAbbreviation(StrUtil.isNotEmpty(notes)?notes:field.getName());
            frontEndVo.setHidden(!hidden);
            linkedList.add((T) frontEndVo);
        }
        return linkedList;
    }
}
