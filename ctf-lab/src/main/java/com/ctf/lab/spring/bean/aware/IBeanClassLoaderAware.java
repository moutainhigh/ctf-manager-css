package com.ctf.lab.spring.bean.aware;

import org.springframework.beans.factory.BeanClassLoaderAware;

/**
 * 获取bean的类加载器
 *
 * @author H.m
 * @date 2022/8/5 10:30
 */
public class IBeanClassLoaderAware implements BeanClassLoaderAware {

    private ClassLoader classLoader;
    @Override
    public void setBeanClassLoader(ClassLoader classLoader) {
        System.out.println("类加载器:"+classLoader);
        this.classLoader= classLoader;
    }
}
