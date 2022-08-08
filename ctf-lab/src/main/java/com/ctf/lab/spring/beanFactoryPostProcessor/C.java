package com.ctf.lab.spring.beanFactoryPostProcessor;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.core.Ordered;

/**
 * 说明描述
 *
 * @author H.m
 * @date 2022/8/5 10:30
 */

public class C implements BeanDefinitionRegistryPostProcessor, Ordered {
    @Override
    public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) throws BeansException {
        System.out.println("C------------BeanDefinitionRegistryPostProcessor---Ordered---postProcessBeanDefinitionRegistry-----2");
    }

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        System.out.println("C------------BeanDefinitionRegistryPostProcessor---Ordered---postProcessBeanFactory-----5");
    }

    @Override
    public int getOrder() {
        return 0;
    }
}
