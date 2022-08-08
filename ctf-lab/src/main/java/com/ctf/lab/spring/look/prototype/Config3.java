package com.ctf.lab.spring.look.prototype;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.context.annotation.ComponentScan;

/**
 * 说明描述
 *
 * @author H.m
 * @date 2022/8/5 10:30
 */
@ComponentScan(basePackages = "com.ctf.lab.spring.look.prototype")
public class Config3 implements BeanDefinitionRegistryPostProcessor {
    @Override
    public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) throws BeansException {
        System.out.println(registry.getBeanDefinition("a3"));
        System.out.println(registry.getBeanDefinition("b3"));
    }

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {

    }
}
