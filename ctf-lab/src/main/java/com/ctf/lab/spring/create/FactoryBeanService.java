package com.ctf.lab.spring.create;

import com.ctf.lab.spring.Bean;

/**
 * 说明描述
 * 实例工厂方法实例化bean
 * @author H.m
 * @date 2022/8/5 10:30
 */
public class FactoryBeanService {
    private static Bean bean = new Bean();
    private FactoryBeanService() {}

    public Bean createInstance() {
        System.out.println("实例工厂方法实例化bean");
        return bean;
    }
}
