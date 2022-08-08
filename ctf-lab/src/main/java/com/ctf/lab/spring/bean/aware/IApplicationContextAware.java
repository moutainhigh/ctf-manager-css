package com.ctf.lab.spring.bean.aware;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 *  获取ApplicationContext
 *
 * @author H.m
 * @date 2022/8/5 10:30
 */
public class IApplicationContextAware implements ApplicationContextAware {
    private ApplicationContext applicationContext;
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
        System.out.println("\n--------------------");
        System.out.println("ApplicationContext:"+applicationContext);
        System.out.println("--------------------");
    }
}
