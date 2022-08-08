package com.ctf.common.mybatis.annotation;

import java.lang.annotation.*;

/**
 * MP数据权限注解
 * <p>
 * https://gitee.com/baomidou/mybatis-plus/issues/I37I90
 *
 * @author H.m
 * @date 2022/8/5 10:30
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.METHOD})
public @interface DataPermission {

    /**
     * 数据权限 {@link com.baomidou.mybatisplus.extension.plugins.inner.DataPermissionInterceptor}
     */
    String deptAlias() default "";
}

