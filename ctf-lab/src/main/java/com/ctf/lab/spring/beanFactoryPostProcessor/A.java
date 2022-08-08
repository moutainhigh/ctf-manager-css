package com.ctf.lab.spring.beanFactoryPostProcessor;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;

/**
 * 说明描述
 *
 * @author H.m
 * @date 2022/8/5 10:30
 */

public class A implements BeanDefinitionRegistryPostProcessor {
    @Override
    public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) throws BeansException {
        System.out.println("A--------------BeanDefinitionRegistryPostProcessor-----postProcessBeanDefinitionRegistry-------3");
    }

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        System.out.println("A-------BeanDefinitionRegistryPostProcessor-----postProcessBeanFactory-----6");
    }
}
