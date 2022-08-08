package com.ctf.lab.spring.beanFactoryPostProcessor;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.core.Ordered;

/**
 * 说明描述
 *
 * @author H.m
 * @date 2022/8/5 10:30
 */

public class F implements BeanFactoryPostProcessor, Ordered {
    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        System.out.println("F------------BeanFactoryPostProcessor--Ordered----postProcessBeanFactory-----8");
    }

    @Override
    public int getOrder() {
        return 0;
    }
}
