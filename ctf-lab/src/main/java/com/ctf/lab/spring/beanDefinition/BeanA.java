package com.ctf.lab.spring.beanDefinition;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * 说明描述
 *
 * @author H.m
 * @date 2022/8/5 10:30
 */
public class BeanA {


    @PreDestroy
    public void destroy(){
        System.out.println("生命周期回调方法，在bean被销毁时调用");
    }

    @PostConstruct
    public void afterPropertiesSet() {
        System.out.println("生命周期回调方法，在bean完成属性注入后调用");
    }
}
