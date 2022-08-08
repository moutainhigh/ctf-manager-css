package com.ctf.lab.spring.beanFactoryPostProcessor;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.core.PriorityOrdered;

/**
 * 说明描述
 *
 * @author H.m
 * @date 2022/8/5 10:30
 */

public class E implements BeanFactoryPostProcessor, PriorityOrdered {
    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        System.out.println("E------------BeanFactoryPostProcessor----PriorityOrdered-------postProcessBeanFactory-----7");
    }

    @Override
    public int getOrder() {
        return 0;
    }
}
