package com.ctf.lab.spring.create;

import com.ctf.lab.spring.Bean;

/**
 *
 * 静态工厂实例化bean
 * @author H.m
 * @date 2022/8/5 10:30
 */
public class FactoryClassService {
    private static Bean bean = new Bean();
    private FactoryClassService() {}
    public static Bean createInstance() {
        System.out.println("静态工厂方法实例化bean");
        return bean;
    }
}
