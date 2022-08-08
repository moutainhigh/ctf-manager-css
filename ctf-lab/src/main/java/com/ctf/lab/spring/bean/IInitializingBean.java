package com.ctf.lab.spring.bean;

import org.springframework.beans.factory.InitializingBean;

/**
 * 说明描述
 *
 * @author H.m
 * @date 2022/8/5 10:30
 */
public class IInitializingBean implements InitializingBean {
    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("初始化bean---InitializingBean");
    }
}
