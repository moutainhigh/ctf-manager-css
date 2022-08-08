package com.ctf.lab.spring.beanPostProcessor;

/**
 *
 * 需要注入的bean
 * @author H.m
 * @date 2022/8/5 10:30
 */
public class Bean{

    private IBeanPostProcessor IBeanPostProcessor;

    public Bean(IBeanPostProcessor IBeanPostProcessor) {
        this.IBeanPostProcessor = IBeanPostProcessor;
        System.out.println("4---实例化Bean");
    }

}
