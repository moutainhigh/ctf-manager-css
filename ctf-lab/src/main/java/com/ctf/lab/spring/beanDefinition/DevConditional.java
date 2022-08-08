package com.ctf.lab.spring.beanDefinition;


import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

import java.util.Objects;

/**
 *
 *  实现注入条件
 * @author H.m
 * @date 2022/8/5 10:30
 */
public class DevConditional implements Condition {

    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
        String property = context.getEnvironment().getProperty("java.version");
        return Objects.equals(property, "1.8.0_312");
    }
}
