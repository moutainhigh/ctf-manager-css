package com.ctf.lab.spring.bean.aware;

import org.springframework.beans.factory.BeanNameAware;

/**
 *  获取bean的名字
 * @author H.m
 * @date 2022/8/5 10:30
 */
public class IBeanNameAware implements BeanNameAware {

    private String name;

    @Override
    public void setBeanName(String name) {
        System.out.println("bean的名字:"+name);
        this.name = name;
    }
}
