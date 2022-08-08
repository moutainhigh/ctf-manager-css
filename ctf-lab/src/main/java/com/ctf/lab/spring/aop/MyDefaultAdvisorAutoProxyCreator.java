package com.ctf.lab.spring.aop;

import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 说明描述
 *
 * @author H.m
 * @date 2022/8/5 10:30
 */
@Configuration
public class MyDefaultAdvisorAutoProxyCreator {

    @Bean
    public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator(){
        return new DefaultAdvisorAutoProxyCreator();

    }
}
