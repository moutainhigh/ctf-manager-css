package com.ctf.lab.spring.aop;

import org.springframework.aop.framework.autoproxy.BeanNameAutoProxyCreator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 通过名称设置自动代理
 *
 * @author H.m
 * @date 2022/8/5 10:30
 */
@Configuration
public class MyBeanNameAutoProxyCreator {

    @Bean
    public BeanNameAutoProxyCreator creator(){
        BeanNameAutoProxyCreator beanNameAutoProxyCreator = new BeanNameAutoProxyCreator();
        beanNameAutoProxyCreator.setBeanNames("userService");
        beanNameAutoProxyCreator.setInterceptorNames("myAdvisor");
        return beanNameAutoProxyCreator;
    }
}
