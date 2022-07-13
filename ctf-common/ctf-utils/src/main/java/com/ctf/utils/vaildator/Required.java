package com.ctf.utils.vaildator;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author william
 * @description
 * @Date: 2020-08-05 16:30
 */
@Target({ElementType.FIELD,  ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface Required {
    //todo 想办法集成check4list，并用@valid统一处理列表校验
}
