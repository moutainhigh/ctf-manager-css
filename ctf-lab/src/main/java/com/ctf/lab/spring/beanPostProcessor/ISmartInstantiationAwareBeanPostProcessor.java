package com.ctf.lab.spring.beanPostProcessor;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.SmartInstantiationAwareBeanPostProcessor;

import java.lang.reflect.Constructor;

/**
 * SmartInstantiationAwareBeanPostProcessor是spring内部接口主要作用为
 * 预测Bean的最终类型
 * 推断构造函数
 * 提前曝光工厂对象,解决循环依赖
 * @author H.m
 * @date 2022/8/5 10:30
 */
public class ISmartInstantiationAwareBeanPostProcessor implements SmartInstantiationAwareBeanPostProcessor {

    @Override
    public Class<?> predictBeanType(Class<?> beanClass, String beanName) throws BeansException {
        System.out.println("1---预测Bean的最终类型"+beanName);
        return null;
    }

    @Override
    public Constructor<?>[] determineCandidateConstructors(Class<?> beanClass, String beanName) throws BeansException {
        System.out.println("3---推断构造函数"+beanName);
        return null;
    }

    @Override
    public Object getEarlyBeanReference(Object bean, String beanName) throws BeansException {
        System.out.println("提前曝光一个工厂对象,用于解决循环依赖"+beanName );
        return bean;
    }
}
