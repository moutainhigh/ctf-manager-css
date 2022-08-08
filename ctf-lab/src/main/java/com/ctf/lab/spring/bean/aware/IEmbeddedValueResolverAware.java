package com.ctf.lab.spring.bean.aware;

import org.springframework.context.EmbeddedValueResolverAware;
import org.springframework.util.StringValueResolver;

/**
 * 说明描述
 *
 * @author H.m
 * @date 2022/8/5 10:30
 */
public class IEmbeddedValueResolverAware implements EmbeddedValueResolverAware {

    private StringValueResolver stringValueResolver;
    @Override
    public void setEmbeddedValueResolver(StringValueResolver resolver) {
        this.stringValueResolver = resolver;
        System.out.println("\n--------------------");
        System.out.println("EmbeddedValueResolverAware:"+resolver);
        System.out.println("--------------------");
    }
}
