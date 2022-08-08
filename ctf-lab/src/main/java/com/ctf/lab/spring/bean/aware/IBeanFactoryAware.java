package com.ctf.lab.spring.bean.aware;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;

/**
 * 获取当前bean的beanFactory
 *
 * @author H.m
 * @date 2022/8/5 10:30
 */
public class IBeanFactoryAware implements BeanFactoryAware {
    private BeanFactory beanFactory;
    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        System.out.println("当前beanFactory:"+beanFactory);
        this.beanFactory = beanFactory;
    }
}
