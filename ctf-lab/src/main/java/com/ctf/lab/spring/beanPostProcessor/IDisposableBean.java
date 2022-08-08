package com.ctf.lab.spring.beanPostProcessor;

import org.springframework.beans.factory.DisposableBean;

/**
 *  销毁bean的回调
 *
 * @author H.m
 * @date 2022/8/5 10:30
 */
public class IDisposableBean implements DisposableBean {
    public IDisposableBean() {
        System.out.println("实例化IDisposableBean");
    }

    @Override
    public void destroy() throws Exception {
        System.out.println("销毁bean回调");
    }
}
