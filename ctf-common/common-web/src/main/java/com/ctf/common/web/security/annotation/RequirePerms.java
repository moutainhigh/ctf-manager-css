package com.ctf.common.web.security.annotation;

import java.lang.annotation.*;

/**
 * 权限校验注解
 *
 * @author H.m
 * @date 2022/8/5 10:30
 */
@Documented
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface RequirePerms {

    String[] value() default {};

}

