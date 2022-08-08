package com.ctf.lab.spring.beanPostProcessor;

import org.springframework.beans.factory.InitializingBean;

/**
 *  初始化bean的回调
 * @author H.m
 * @date 2022/8/5 10:30
 */
public class IInitializingBean implements InitializingBean {
    public IInitializingBean() {
        System.out.println("实例化IInitializingBean");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("属性注入之后");
    }
}
