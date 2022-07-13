package com.ctf.utils.vaildator;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 标记需要对请求参数中的列表进行查重
 * @author william
 * @description
 * @Date: 2020-07-29 16:52
 */
@Target({ElementType.FIELD,  ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface CheckRequestList {
    /**
     * 不可重复的字段
     *
     * @return
     */
    String[] notDuplicate() default {};

    String[] notNullnotDuplicate() default {};

}
